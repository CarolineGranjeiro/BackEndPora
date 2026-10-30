package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.model.CatalogoDefeito;

public record DefeitoResponseDTO(
        Integer id,
        String nome,
        String descricao,
        String codigoDefeito,
        String severidade
) {
    public DefeitoResponseDTO(CatalogoDefeito defeito) {
        this(
                defeito.getId(),
                defeito.getNome(),
                defeito.getDescricao(),
                defeito.getCodigoDefeito(),
                defeito.getSeveridade()
        );
    }
}