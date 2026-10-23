package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.enums.PerfilUsuario;
import com.inspecao.inspecao_dormentes.model.Usuario;

// Dados devolvidos pela API (sem expor a senha)
public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        PerfilUsuario perfil
) {
    // Construtor auxiliar para converter a entidade Usuario diretamente em DTO- Data Transfer Objects
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPerfil());
    }
}