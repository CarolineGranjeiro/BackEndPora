package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DormenteRequestDTO(
        @NotBlank(message = "O número de série é obrigatório.")
        @Size(max = 50, message = "O número de série deve ter no máximo 50 caracteres.")
        String numeroSerie,

        @Size(max = 100, message = "O QR Code deve ter no máximo 100 caracteres.")
        String qrCode,

        @NotBlank(message = "O tipo de cadastro é obrigatório.")
        @Pattern(
                regexp = "LOTE|INDIVIDUAL",
                message = "O tipo de cadastro deve ser LOTE ou INDIVIDUAL."
        )
        String tipoCadastro,

        @NotNull(message = "O lote é obrigatório.")
        @Positive(message = "O ID do lote deve ser positivo.")
        Integer loteId,

        @NotNull(message = "O contrato é obrigatório.")
        @Positive(message = "O ID do contrato deve ser positivo.")
        Integer contratoId,

        @NotNull(message = "A localização é obrigatória.")
        @Positive(message = "O ID da localização deve ser positivo.")
        Integer localizacaoId,

        @NotBlank(message = "O material é obrigatório.")
        @Size(max = 50, message = "O material deve ter no máximo 50 caracteres.")
        String material,

        @NotBlank(message = "A descrição é obrigatória.")
        @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.")
        String descricao,

        @Size(max = 50, message = "O tipo de madeira deve ter no máximo 50 caracteres.")
        String tipoMadeira,

        @Size(max = 50, message = "A classe da madeira deve ter no máximo 50 caracteres.")
        String classeMadeira,

        @Size(max = 100, message = "A espécie deve ter no máximo 100 caracteres.")
        String especie,

        @NotNull(message = "O comprimento é obrigatório.")
        @Positive(message = "O comprimento deve ser maior que zero.")
        Double comprimento,

        @NotNull(message = "A largura é obrigatória.")
        @Positive(message = "A largura deve ser maior que zero.")
        Double largura,

        @NotNull(message = "A altura é obrigatória.")
        @Positive(message = "A altura deve ser maior que zero.")
        Double altura,

        @Size(max = 30, message = "O status deve ter no máximo 30 caracteres.")
        String status
) {
}