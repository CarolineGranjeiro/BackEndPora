package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LocalizacaoRequestDTO(
        @NotBlank(message = "O pátio é obrigatório.")
        @Size(max = 100, message = "O pátio deve ter no máximo 100 caracteres.")
        String patio,

        @NotBlank(message = "O setor é obrigatório.")
        @Size(max = 100, message = "O setor deve ter no máximo 100 caracteres.")
        String setor,

        @NotBlank(message = "A posição é obrigatória.")
        @Size(max = 100, message = "A posição deve ter no máximo 100 caracteres.")
        String posicao,

        @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.")
        String descricao
) {
}