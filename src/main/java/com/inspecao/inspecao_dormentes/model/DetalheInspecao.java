package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "detalhes_inspecao",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_inspecao_defeito",
                columnNames = {"inspecao_id", "defeito_id"}
        )
)
public class DetalheInspecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspecao_id", nullable = false)
    private Inspecao inspecao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "defeito_id", nullable = false)
    private CatalogoDefeito defeito;

    @Column(name = "observacao", length = 255)
    private String observacao;

    public DetalheInspecao() {}

    public DetalheInspecao(Inspecao inspecao, CatalogoDefeito defeito, String observacao) {
        this.inspecao = inspecao;
        this.defeito = defeito;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Inspecao getInspecao() {
        return inspecao;
    }

    public void setInspecao(Inspecao inspecao) {
        this.inspecao = inspecao;
    }

    public CatalogoDefeito getDefeito() {
        return defeito;
    }

    public void setDefeito(CatalogoDefeito defeito) {
        this.defeito = defeito;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}