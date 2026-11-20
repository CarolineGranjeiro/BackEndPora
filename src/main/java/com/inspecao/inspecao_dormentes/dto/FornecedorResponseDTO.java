
package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.model.Fornecedor;

public record FornecedorResponseDTO(
        Integer id,
        String nome,
        String cnpj,
        String contato
) {

    public FornecedorResponseDTO(Fornecedor fornecedor) {
        this(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getCnpj(),
                fornecedor.getContato()
        );
    }
}
