package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "localizacoes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_localizacao_patio_setor_posicao",
                        columnNames = {"patio", "setor", "posicao"}
                )
        }
)
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "patio", nullable = false, length = 100)
    private String patio;

    @Column(name = "setor", nullable = false, length = 100)
    private String setor;

    @Column(name = "posicao", nullable = false, length = 100)
    private String posicao;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public Localizacao() {
    }

    public Localizacao(
            String patio,
            String setor,
            String posicao,
            String descricao) {

        this.patio = patio;
        this.setor = setor;
        this.posicao = posicao;
        this.descricao = descricao;
    }

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatio() {
        return patio;
    }

    public void setPatio(String patio) {
        this.patio = patio;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}