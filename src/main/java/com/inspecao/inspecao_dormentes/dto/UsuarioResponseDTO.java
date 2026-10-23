package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.enums.PerfilUsuario;
import com.inspecao.inspecao_dormentes.model.Usuario;

public record UsuarioResponseDTO(
        Integer id,
        String nome,
        String email,
        PerfilUsuario perfil
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPerfil());
    }
}