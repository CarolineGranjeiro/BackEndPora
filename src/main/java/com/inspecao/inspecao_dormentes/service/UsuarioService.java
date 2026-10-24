package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.UsuarioRequestDTO;
import com.inspecao.inspecao_dormentes.dto.UsuarioResponseDTO;
import com.inspecao.inspecao_dormentes.model.Usuario;
import com.inspecao.inspecao_dormentes.repository.UsuarioRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            org.springframework.security.crypto.password.PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        String senhaCriptografada = passwordEncoder.encode(dto.senha());

        Usuario novoUsuario = new Usuario(
                dto.nome(),
                dto.email(),
                senhaCriptografada,
                dto.perfil()
        );

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        return new UsuarioResponseDTO(usuarioSalvo);
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponseDTO::new)
                .toList();

    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID: " + id));
        return new UsuarioResponseDTO(usuario);
    }

    @org.springframework.transaction.annotation.Transactional
    public UsuarioResponseDTO atualizar(Integer id, com.inspecao.inspecao_dormentes.dto.UsuarioAtualizacaoDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID: " + id));

        usuario.setNome(dto.nome());
        usuario.setPerfil(dto.perfil());

        return new UsuarioResponseDTO(usuarioRepository.save(usuario));
    }

    @org.springframework.transaction.annotation.Transactional
    public void eliminar(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado para o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // ==================== MÉTODOS DE RECUPERAÇÃO DE SENHA ====================

    @org.springframework.transaction.annotation.Transactional
    public String solicitarRecuperacao(String email) {
        // 1. Busca o usuário pelo e-mail
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("E-mail não encontrado no sistema."));

        // 2. Gera um token único e define expiração para 15 minutos
        String token = java.util.UUID.randomUUID().toString();
        usuario.setTokenRecuperacao(token);
        usuario.setExpiracaoToken(java.time.LocalDateTime.now().plusMinutes(15));

        // 3. Salva no banco e retorna o token
        usuarioRepository.save(usuario);
        return token;
    }

    @org.springframework.transaction.annotation.Transactional
    public void redefinirSenha(String token, String novaSenha) {
        // 1. Busca o usuário pelo token de recuperação
        Usuario usuario = usuarioRepository.findByTokenRecuperacao(token)
                .orElseThrow(() -> new IllegalArgumentException("Token inválido ou não encontrado."));

        // 2. Verifica se o token já expirou
        if (usuario.getExpiracaoToken() == null || java.time.LocalDateTime.now().isAfter(usuario.getExpiracaoToken())) {
            throw new IllegalArgumentException("Token expirado. Solicite uma nova recuperação.");
        }

        // 3. Criptografa a nova senha com BCrypt
        usuario.setSenha(passwordEncoder.encode(novaSenha));

        // 4. Limpa o token para não permitir reutilização
        usuario.setTokenRecuperacao(null);
        usuario.setExpiracaoToken(null);

        usuarioRepository.save(usuario);
    }
    }
