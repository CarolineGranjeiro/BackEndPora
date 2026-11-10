package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inspecoes")
public class Inspecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dormente_id", nullable = false)
    private Dormente dormente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "data_inspecao", nullable = false)
    private LocalDateTime dataInspecao;

    @Column(name = "quantidade_observada", nullable = false)
    private Integer quantidadeObservada;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public Inspecao() {
    }

    public Inspecao(
            Dormente dormente,
            Usuario usuario,
            LocalDateTime dataInspecao,
            Integer quantidadeObservada,
            String status,
            String observacoes) {
        this.dormente = dormente;
        this.usuario = usuario;
        this.dataInspecao = dataInspecao;
        this.quantidadeObservada = quantidadeObservada;
        this.status = status;
        this.observacoes = observacoes;
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime agora = LocalDateTime.now();
        if (this.dataInspecao == null) {
            this.dataInspecao = agora;
        }
        if (this.criadoEm == null) {
            this.criadoEm = agora;
        }
    }

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dormente getDormente() {
        return dormente;
    }

    public void setDormente(Dormente dormente) {
        this.dormente = dormente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataInspecao() {
        return dataInspecao;
    }

    public void setDataInspecao(LocalDateTime dataInspecao) {
        this.dataInspecao = dataInspecao;
    }

    public Integer getQuantidadeObservada() {
        return quantidadeObservada;
    }

    public void setQuantidadeObservada(Integer quantidadeObservada) {
        this.quantidadeObservada = quantidadeObservada;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    // Métodos de compatibilidade com versões antigas
    public String getResultadoGeral() {
        return status;
    }

    public void setResultadoGeral(String resultadoGeral) {
        this.status = resultadoGeral;
    }
}