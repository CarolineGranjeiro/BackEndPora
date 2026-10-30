package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dormentes")
public class Dormente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "qr_code", unique = true, nullable = false)
    private String qrCode;

    @Column(name = "tipo_madeira")
    private String tipoMadeira;

    @Column(name = "classe_madeira")
    private String classeMadeira;

    private Double comprimento;
    private String status;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "lote_id")
    private Lote lote;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

    public Dormente() {
    }

    public Dormente(String numeroSerie, String qrCode, String tipoMadeira, String classeMadeira, Double comprimento, String status, LocalDateTime criadoEm, Lote lote, Contrato contrato) {
        this.numeroSerie = numeroSerie;
        this.qrCode = qrCode;
        this.tipoMadeira = tipoMadeira;
        this.classeMadeira = classeMadeira;
        this.comprimento = comprimento;
        this.status = status;
        this.criadoEm = criadoEm;
        this.lote = lote;
        this.contrato = contrato;
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

    public Lote getLote() { return lote; }
    public void setLote(Lote lote) { this.lote = lote; }

    public Contrato getContrato() { return contrato; }
    public void setContrato(Contrato contrato) { this.contrato = contrato; }
}