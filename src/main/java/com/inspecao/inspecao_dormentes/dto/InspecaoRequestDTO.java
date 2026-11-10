package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record InspecaoRequestDTO(
        @NotNull(message = "O dormente é obrigatório.")
        @Positive(message = "O ID do dormente deve ser positivo.")
        Integer dormenteId,

        @NotNull(message = "A data da inspeção é obrigatória.")
        LocalDateTime dataInspecao,

        @NotNull(message = "A quantidade observada é obrigatória.")
        @Positive(message = "A quantidade observada deve ser maior que zero.")
        Integer quantidadeObservada,

        @NotBlank(message = "A condição observada é obrigatória.")
        @Size(
                max = 50,
                message = "A condição deve ter no máximo 50 caracteres."
        )
        String status,

        String observacoes,

        @NotNull(message = "Informe a lista de defeitos, mesmo que vazia.")
        List<@NotNull @Valid DetalheInspecaoRequestDTO> detalhes
) {
}