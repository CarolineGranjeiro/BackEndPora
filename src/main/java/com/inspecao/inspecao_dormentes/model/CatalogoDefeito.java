package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "catalogo_defeitos")
public class CatalogoDefeito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "codigo_defeito", length = 20)
    private String codigoDefeito;

    @Column(name = "severidade", length = 20)
    private String severidade;

    public CatalogoDefeito() {}

    public CatalogoDefeito(String nome, String descricao, String codigoDefeito, String severidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigoDefeito = codigoDefeito;
        this.severidade = severidade;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoDefeito() {
        return codigoDefeito;
    }

    public void setCodigoDefeito(String codigoDefeito) {
        this.codigoDefeito = codigoDefeito;
    }

    public String getSeveridade() {
        return severidade;
    }

    public void setSeveridade(String severidade) {
        this.severidade = severidade;
    }
}