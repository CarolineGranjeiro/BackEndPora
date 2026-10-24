package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.UsuarioRequestDTO;
import com.inspecao.inspecao_dormentes.dto.UsuarioResponseDTO;
import com.inspecao.inspecao_dormentes.model.Usuario;
import com.inspecao.inspecao_dormentes.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        Usuario novoUsuario = new Usuario(
                dto.nome(),
                dto.email(),
                dto.senha(),
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
}
