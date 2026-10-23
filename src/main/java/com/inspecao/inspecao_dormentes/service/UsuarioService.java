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
}