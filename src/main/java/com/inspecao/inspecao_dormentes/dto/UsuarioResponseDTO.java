package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.enums.PerfilUsuario;
import com.inspecao.inspecao_dormentes.model.Usuario;

// Dados devolvidos pela API (sem expor a senha e com o ID como Integer)
public record UsuarioResponseDTO(
        Integer id,
        String nome,
        String email,
        PerfilUsuario perfil
) {
    // Construtor auxiliar para converter a entidade Usuario diretamente em DTO
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPerfil());
    }
}