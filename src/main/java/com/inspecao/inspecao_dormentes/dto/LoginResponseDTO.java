package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.enums.PerfilUsuario;

public record LoginResponseDTO(
        Integer id,
        String nome,
        String email,
        PerfilUsuario perfil,
        String token
) {}