package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dormentes")
public class Dormente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_serie", nullable = false, unique = true, length = 50)
    private String numeroSerie;

    @Column(name = "qr_code", unique = true, length = 100)
    private String qrCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id", nullable = false)
    private Lote lote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;

    @Column(name = "tipo_madeira", length = 50)
    private String tipoMadeira;

    @Column(name = "classe_madeira", length = 50)
    private String classeMadeira;

    @Column(name = "comprimento")
    private Double comprimento;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }

    public Dormente() {
    }

    public Dormente(String numeroSerie, String qrCode, Lote lote, Contrato contrato,
                    String tipoMadeira, String classeMadeira, Double comprimento, String status) {
        this.numeroSerie = numeroSerie;
        this.qrCode = qrCode;
        this.lote = lote;
        this.contrato = contrato;
        this.tipoMadeira = tipoMadeira;
        this.classeMadeira = classeMadeira;
        this.comprimento = comprimento;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public Lote getLote() {
        return lote;
    }
    public void setLote(Lote lote) {
        this.lote = lote;
    }
    public Contrato getContrato() {
        return contrato;
    }
    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    public String getTipoMadeira() {
        return tipoMadeira;
    }
    public void setTipoMadeira(String tipoMadeira) {
        this.tipoMadeira = tipoMadeira;
    }
    public String getClasseMadeira() {
        return classeMadeira;
    }
    public void setClasseMadeira(String classeMadeira) {
        this.classeMadeira = classeMadeira;
    }
    public Double getComprimento() {
        return comprimento;
    }
    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento; }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
