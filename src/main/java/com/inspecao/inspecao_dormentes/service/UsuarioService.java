package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.UsuarioRequestDTO;
import com.inspecao.inspecao_dormentes.dto.UsuarioResponseDTO;
import com.inspecao.inspecao_dormentes.model.Usuario;
import com.inspecao.inspecao_dormentes.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica que esta classe contém as regras de negócio
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Injeção do repositório pelo construtor
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Regra para cadastrar um novo usuário
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        Usuario novoUsuario = new Usuario(
                dto.nome(),
                dto.email(),
                dto.senha(), // Posteriormente podemos aplicar o hash/criptografia aqui
                dto.perfil()
        );

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        return new UsuarioResponseDTO(usuarioSalvo);
    }

    // Regra para listar todos os usuários
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }
}