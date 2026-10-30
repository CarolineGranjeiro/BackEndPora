package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;

public record DefeitoRequestDTO(
        @NotBlank(message = "O nome do defeito é obrigatório")
        String nome,

        String descricao,

        @NotBlank(message = "O código do defeito é obrigatório")
        String codigoDefeito,

        @NotBlank(message = "A severidade é obrigatória")
        String severidade
) {}