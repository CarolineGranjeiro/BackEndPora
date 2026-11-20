
package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.model.Contrato;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContratoResponseDTO(
        Integer id,
        String numeroContrato,
        Integer fornecedorId,
        LocalDate dataInicio,
        LocalDate dataFim,
        LocalDateTime criadoEm
) {

    public ContratoResponseDTO(Contrato contrato) {
        this(
                contrato.getId(),
                contrato.getNumeroContrato(),
                contrato.getFornecedor().getId(),
                contrato.getDataInicio(),
                contrato.getDataFim(),
                contrato.getCriadoEm()
        );
    }
}
