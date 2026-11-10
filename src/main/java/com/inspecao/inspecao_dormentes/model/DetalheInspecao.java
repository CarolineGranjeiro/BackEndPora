package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;

@Entity                                // esta classe representa uma tabela
@Table(name = "detalhes_inspecao")     // nome da tabela no banco
public class DetalheInspecao {

    @Id                                                   // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // o MySQL gera o id (AUTO_INCREMENT)
    private Integer id;

    // Vários detalhes -> uma inspeção (coluna inspecao_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspecao_id", nullable = false)
    private Inspecao inspecao;

    // Vários detalhes -> um defeito do catálogo (coluna defeito_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "defeito_id", nullable = false)
    private CatalogoDefeito defeito;

    @Column(name = "observacao")
    private String observacao;


    // Construtores

    public DetalheInspecao() {
        // o JPA exige um construtor vazio
    }

    public DetalheInspecao(Inspecao inspecao, CatalogoDefeito defeito, String observacao) {
        this.inspecao = inspecao;
        this.defeito = defeito;
        this.observacao = observacao;
    }


    // Getters e setters

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

