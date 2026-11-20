
package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ContratoRequestDTO(

        @NotBlank(message = "O número do contrato é obrigatório.")
        @Size(max = 50, message = "O número do contrato deve ter no máximo 50 caracteres.")
        String numeroContrato,

        @NotNull(message = "O ID do fornecedor é obrigatório.")
        @Positive(message = "O ID do fornecedor deve ser maior que zero.")
        Integer fornecedorId,

        LocalDate dataInicio,

        LocalDate dataFim

) {
}
