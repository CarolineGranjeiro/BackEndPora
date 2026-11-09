package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.model.HistoricoLog;
import java.time.LocalDateTime;

public record HistoricoLogResponseDTO(
        Integer id,
        String codigoAtividade,
        Integer usuarioId,
        String nomeUsuario,
        String acao,
        String entidade,
        Integer entidadeId,
        String descricao,
        String dadosAnteriores,
        String dadosNovos,
        LocalDateTime criadoEm
) {
    public HistoricoLogResponseDTO(HistoricoLog log) {
        this(
                log.getId(),
                log.getCodigoAtividade(),
                log.getUsuario().getId(),
                log.getUsuario().getNome(),
                log.getAcao(),
                log.getEntidade(),
                log.getEntidadeId(),
                log.getDescricao(),
                log.getDadosAnteriores(),
                log.getDadosNovos(),
                log.getCriadoEm()
        );
    }
}