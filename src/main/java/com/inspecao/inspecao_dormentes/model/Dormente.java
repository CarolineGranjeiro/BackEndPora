package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @Column(name = "tipo_cadastro", nullable = false, length = 20)
    private String tipoCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id", nullable = false)
    private Lote lote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localizacao_id", nullable = false)
    private Localizacao localizacao;

    @Column(name = "material", nullable = false, length = 50)
    private String material;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "tipo_madeira", length = 50)
    private String tipoMadeira;

    @Column(name = "classe_madeira", length = 50)
    private String classeMadeira;

    @Column(name = "especie", length = 100)
    private String especie;

    @Column(name = "comprimento", nullable = false)
    private Double comprimento;

    @Column(name = "largura", nullable = false)
    private Double largura;

    @Column(name = "altura", nullable = false)
    private Double altura;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;


    public Dormente() {
    }

    public Dormente(
            String numeroSerie,
            String qrCode,
            String tipoCadastro,
            Lote lote,
            Contrato contrato,
            Localizacao localizacao,
            String material,
            String descricao,
            String tipoMadeira,
            String classeMadeira,
            String especie,
            Double comprimento,
            Double largura,
            Double altura,
            String status) {

        this.numeroSerie = numeroSerie;
        this.qrCode = qrCode;
        this.tipoCadastro = tipoCadastro;
        this.lote = lote;
        this.contrato = contrato;
        this.localizacao = localizacao;
        this.material = material;
        this.descricao = descricao;
        this.tipoMadeira = tipoMadeira;
        this.classeMadeira = classeMadeira;
        this.especie = especie;
        this.comprimento = comprimento;
        this.largura = largura;
        this.altura = altura;
        this.status = status;
    }


    @PrePersist
    public void prePersist() {
        LocalDateTime agora = LocalDateTime.now();

        this.criadoEm = agora;
        this.atualizadoEm = agora;

        if (this.qrCode == null || this.qrCode.isBlank()) {
            this.qrCode = UUID.randomUUID().toString();
        }
    }


    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
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

    public String getTipoCadastro() {
        return tipoCadastro;
    }

    public void setTipoCadastro(String tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
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

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Double getComprimento() {
        return comprimento;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

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

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}