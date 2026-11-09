package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.model.Relatorio;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record RelatorioResponseDTO(
        Integer id,
        Integer usuarioId,
        String nomeUsuario,
        String tipo,
        String formato,
        String status,
        LocalDate dataInicio,
        LocalDate dataFim,
        String material,
        Integer localizacaoId,
        String graficosSelecionados,
        LocalDateTime criadoEm,
        LocalDateTime concluidoEm
) {
    public RelatorioResponseDTO(Relatorio relatorio) {
        this(
                relatorio.getId(),
                relatorio.getUsuario().getId(),
                relatorio.getUsuario().getNome(),
                relatorio.getTipo(),
                relatorio.getFormato(),
                relatorio.getStatus(),
                relatorio.getDataInicio(),
                relatorio.getDataFim(),
                relatorio.getMaterial(),
                relatorio.getLocalizacaoId(),
                relatorio.getGraficosSelecionados(),
                relatorio.getCriadoEm(),
                relatorio.getConcluidoEm()
        );
    }
}