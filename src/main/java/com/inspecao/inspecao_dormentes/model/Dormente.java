package com.inspecao.inspecao_dormentes.model;
import com.inspecao.inspecao_dormentes.enums.StatusDormente;

import java.time.LocalDate;

public class Dormente {
    private Long id;
    private String numeroSerie;
    private String qrCode;
    private Long loteId;
    private Long contratoId;
    private String tipoMadeira;
    private String classeMadeira;
    private double comprimento;
    private StatusDormente status;
    private LocalDate criadoEm;


    //construtores

    public Dormente(){

    }

    public Dormente(String numeroSerie, String qrCode, Long loteId, Long contratoId,
                    String tipoMadeira, String classeMadeira, double comprimento, StatusDormente status) {
        this.numeroSerie = numeroSerie;
        this.qrCode = qrCode;
        this.loteId = loteId;
        this.contratoId = contratoId;
        this.tipoMadeira = tipoMadeira;
        this.classeMadeira = classeMadeira;
        this.comprimento = comprimento;
        this.status = status;
        this.criadoEm = LocalDate.now();
    }

    // getters e setter
    public String getClasseMadeira() {
        return classeMadeira;
    }

    public void setClasseMadeira(String classeMadeira) {
        this.classeMadeira = classeMadeira;
    }

    public String getTipoMadeira() {
        return tipoMadeira;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public Long getContratoId() {
        return contratoId;
    }

    public void setContratoId(Long contratoId) {
        this.contratoId = contratoId;
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDate criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoteId() {
        return loteId;
    }

    public void setLoteId(Long loteId) {
        this.loteId = loteId;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public StatusDormente getStatus() {
        return status;
    }

    public void setStatus(StatusDormente status) {
        this.status = status;
    }

    public void setTipoMadeira(String tipoMadeira) {
        this.tipoMadeira = tipoMadeira;
    }
}
