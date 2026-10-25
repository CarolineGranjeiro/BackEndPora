package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.enums.PerfilUsuario;

// Dados recebidos na requisição de cadastro
public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha,
        PerfilUsuario perfil
) {}