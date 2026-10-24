package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.enums.PerfilUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioAtualizacaoDTO(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 150, message = "O nome não pode exceder 150 caracteres.")
        String nome,

        @NotNull(message = "O perfil é obrigatório.")
        PerfilUsuario perfil
) {}
