package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.model.DetalheInspecao;

public record DetalheInspecaoResponseDTO(
        Integer id,
        Integer inspecaoId,
        Integer defeitoId,
        String nomeDefeito,
        String observacao
) {

    public DetalheInspecaoResponseDTO(DetalheInspecao detalhe) {
        this(
                detalhe.getId(),
                detalhe.getInspecao().getId(),
                detalhe.getDefeito().getId(),
                detalhe.getDefeito().getNome(),
                detalhe.getObservacao()
        );
    }
}