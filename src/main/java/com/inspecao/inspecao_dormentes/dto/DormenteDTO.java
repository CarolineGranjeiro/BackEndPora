package com.inspecao.inspecao_dormentes.dto;

import java.time.LocalDateTime;

public class DormenteDTO {

    private Long id;
    private String numeroSerie;
    private String qrCode;
    private String tipoMadeira;
    private String classeMadeira;
    private Double comprimento;
    private String status;
    private LocalDateTime criadoEm;
    private Long loteId;
    private Long contratoId;

    public DormenteDTO() {
    }

    public DormenteDTO(Long id, String numeroSerie, String qrCode, String tipoMadeira, String classeMadeira, Double comprimento, String status, LocalDateTime criadoEm, Long loteId, Long contratoId) {
        this.id = id;
        this.numeroSerie = numeroSerie;
        this.qrCode = qrCode;
        this.tipoMadeira = tipoMadeira;
        this.classeMadeira = classeMadeira;
        this.comprimento = comprimento;
        this.status = status;
        this.criadoEm = criadoEm;
        this.loteId = loteId;
        this.contratoId = contratoId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroSerie() { return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public String getTipoMadeira() { return tipoMadeira; }
    public void setTipoMadeira(String tipoMadeira) { this.tipoMadeira = tipoMadeira; }

    public String getClasseMadeira() { return classeMadeira; }
    public void setClasseMadeira(String classeMadeira) { this.classeMadeira = classeMadeira; }

    public Double getComprimento() { return comprimento; }
    public void setComprimento(Double comprimento) { this.comprimento = comprimento; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public Long getLoteId() { return loteId; }
    public void setLoteId(Long loteId) { this.loteId = loteId; }

    public Long getContratoId() { return contratoId; }
    public void setContratoId(Long contratoId) { this.contratoId = contratoId; }
}