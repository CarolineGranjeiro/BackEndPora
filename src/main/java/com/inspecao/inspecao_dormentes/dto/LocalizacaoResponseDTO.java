package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.model.Localizacao;

import java.time.LocalDateTime;

public record LocalizacaoResponseDTO(
        Integer id,
        String patio,
        String setor,
        String posicao,
        String descricao,
        LocalDateTime criadoEm
) {

    public LocalizacaoResponseDTO(Localizacao localizacao) {
        this(
                localizacao.getId(),
                localizacao.getPatio(),
                localizacao.getSetor(),
                localizacao.getPosicao(),
                localizacao.getDescricao(),
                localizacao.getCriadoEm()
        );
    }
}