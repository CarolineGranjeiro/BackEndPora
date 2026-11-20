
package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LoteRequestDTO(

        @NotBlank(message = "O código do lote é obrigatório.")
        @Size(max = 50, message = "O código do lote deve ter no máximo 50 caracteres.")
        String codigoLote,

        @NotNull(message = "O ID do fornecedor é obrigatório.")
        @Positive(message = "O ID do fornecedor deve ser maior que zero.")
        Integer fornecedorId,

        LocalDate dataRecebimento

) {
}
