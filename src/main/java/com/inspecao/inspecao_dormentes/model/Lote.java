package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lotes")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_lote", nullable = false, unique = true, length = 50)
    private String codigoLote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @Column(name = "data_recebimento")
    private LocalDate dataRecebimento;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @OneToMany(mappedBy = "lote")
    private List<Dormente> dormentes;

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }

    public Lote() {
    }

    public Lote(String codigoLote, Fornecedor fornecedor, LocalDate dataRecebimento) {
        this.codigoLote = codigoLote;
        this.fornecedor = fornecedor;
        this.dataRecebimento = dataRecebimento;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCodigoLote() {
        return codigoLote;
    }
    public void setCodigoLote(String codigoLote) {
        this.codigoLote = codigoLote;
    }
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    public LocalDate getDataRecebimento() {
        return dataRecebimento;
    }
    public void setDataRecebimento(LocalDate dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }
    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public List<Dormente> getDormentes() {
        return dormentes; }
    public void setDormentes(List<Dormente> dormentes) {
        this.dormentes = dormentes;
    }
}