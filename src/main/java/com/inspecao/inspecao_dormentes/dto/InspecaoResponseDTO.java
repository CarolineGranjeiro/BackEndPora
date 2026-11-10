package com.inspecao.inspecao_dormentes.dto;

import java.time.LocalDateTime;
import java.util.List;

public record InspecaoResponseDTO(
        Integer id,
        Integer dormenteId,
        Integer usuarioId,
        LocalDateTime dataInspecao,
        Integer quantidadeObservada,
        String status,
        String observacoes,
        LocalDateTime criadoEm,
        List<DetalheInspecaoResponseDTO> detalhes
) {
}