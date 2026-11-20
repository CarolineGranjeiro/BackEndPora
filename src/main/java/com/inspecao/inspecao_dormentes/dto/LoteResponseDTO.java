
package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.model.Lote;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LoteResponseDTO(
        Integer id,
        String codigoLote,
        Integer fornecedorId,
        LocalDate dataRecebimento,
        LocalDateTime criadoEm
) {

    public LoteResponseDTO(Lote lote) {
        this(
                lote.getId(),
                lote.getCodigoLote(),
                lote.getFornecedor().getId(),
                lote.getDataRecebimento(),
                lote.getCriadoEm()
        );
    }
}
