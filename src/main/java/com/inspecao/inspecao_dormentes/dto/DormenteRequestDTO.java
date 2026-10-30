package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DormenteRequestDTO {

    @NotBlank(message = "O número de série é obrigatório.")
    @Size(max = 50, message = "O número de série deve ter no máximo 50 caracteres.")
    private String numeroSerie;

    @Size(max = 100, message = "O QR Code deve ter no máximo 100 caracteres.")
    private String qrCode;

    @NotNull(message = "O ID do lote é obrigatório.")
    private Integer loteId;

    @NotNull(message = "O ID do contrato é obrigatório.")
    private Integer contratoId;

    @Size(max = 50, message = "O tipo de madeira deve ter no máximo 50 caracteres.")
    private String tipoMadeira;

    @Size(max = 50, message = "A classe da madeira deve ter no máximo 50 caracteres.")
    private String classeMadeira;

    private Double comprimento;

    @Size(max = 30, message = "O status deve ter no máximo 30 caracteres.")
    private String status;

    public DormenteRequestDTO() {
    }

    public String getNumeroSerie() { return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public Integer getLoteId() { return loteId; }
    public void setLoteId(Integer loteId) { this.loteId = loteId; }

    public Integer getContratoId() { return contratoId; }
    public void setContratoId(Integer contratoId) { this.contratoId = contratoId; }

    public String getTipoMadeira() { return tipoMadeira; }
    public void setTipoMadeira(String tipoMadeira) { this.tipoMadeira = tipoMadeira; }

    public String getClasseMadeira() { return classeMadeira; }
    public void setClasseMadeira(String classeMadeira) { this.classeMadeira = classeMadeira; }

    public Double getComprimento() { return comprimento; }
    public void setComprimento(Double comprimento) { this.comprimento = comprimento; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}