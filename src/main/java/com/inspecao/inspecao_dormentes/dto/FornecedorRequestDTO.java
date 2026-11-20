
package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FornecedorRequestDTO(

        @NotBlank(message = "O nome do fornecedor é obrigatório.")
        @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
        String nome,

        @NotBlank(message = "O CNPJ é obrigatório.")
        @Size(max = 20, message = "O CNPJ deve ter no máximo 20 caracteres.")
        String cnpj,

        @Size(max = 100, message = "O contato deve ter no máximo 100 caracteres.")
        String contato

) {
}
