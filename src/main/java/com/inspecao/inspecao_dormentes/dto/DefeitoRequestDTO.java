package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DefeitoRequestDTO(
        @NotBlank(message = "O nome do defeito é obrigatório")
        @Size(max = 100, message = "O nome não pode exceder 100 caracteres")
        String nome,

        String descricao,

        @NotBlank(message = "O código do defeito é obrigatório")
        @Size(max = 20, message = "O código do defeito não pode exceder 20 caracteres")
        String codigoDefeito,

        @NotBlank(message = "A severidade é obrigatória")
        @Size(max = 20, message = "A severidade não pode exceder 20 caracteres")
        String severidade
) {}