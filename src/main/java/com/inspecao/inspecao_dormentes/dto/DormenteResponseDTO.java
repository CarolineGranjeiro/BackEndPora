package com.inspecao.inspecao_dormentes.dto;

import com.inspecao.inspecao_dormentes.model.Dormente;

import java.time.LocalDateTime;

public record DormenteResponseDTO(
        Integer id,
        String numeroSerie,
        String qrCode,
        String tipoCadastro,
        Integer loteId,
        Integer contratoId,
        Integer localizacaoId,
        String material,
        String descricao,
        String tipoMadeira,
        String classeMadeira,
        String especie,
        Double comprimento,
        Double largura,
        Double altura,
        String status,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {

    public DormenteResponseDTO(Dormente dormente) {
        this(
                dormente.getId(),
                dormente.getNumeroSerie(),
                dormente.getQrCode(),
                dormente.getTipoCadastro(),
                dormente.getLote().getId(),
                dormente.getContrato().getId(),
                dormente.getLocalizacao().getId(),
                dormente.getMaterial(),
                dormente.getDescricao(),
                dormente.getTipoMadeira(),
                dormente.getClasseMadeira(),
                dormente.getEspecie(),
                dormente.getComprimento(),
                dormente.getLargura(),
                dormente.getAltura(),
                dormente.getStatus(),
                dormente.getCriadoEm(),
                dormente.getAtualizadoEm()
        );
    }
}