package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DetalheInspecaoRequestDTO(
        @NotNull(message = "O defeito é obrigatório.")
        @Positive(message = "O ID do defeito deve ser positivo.")
        Integer defeitoId,

        @Size(
                max = 255,
                message = "A observação deve ter no máximo 255 caracteres."
        ) String observacao) {
}