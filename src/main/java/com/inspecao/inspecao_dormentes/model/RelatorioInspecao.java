package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "relatorio_inspecoes",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_relatorio_inspecao",
                columnNames = {"relatorio_id", "inspecao_id"}
        )
)
public class RelatorioInspecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relatorio_id", nullable = false)
    private Relatorio relatorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspecao_id", nullable = false)
    private Inspecao inspecao;

    public RelatorioInspecao() {}

    public RelatorioInspecao(Relatorio relatorio, Inspecao inspecao) {
        this.relatorio = relatorio;
        this.inspecao = inspecao;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Relatorio getRelatorio() { return relatorio; }
    public void setRelatorio(Relatorio relatorio) { this.relatorio = relatorio; }

    public Inspecao getInspecao() { return inspecao; }
    public void setInspecao(Inspecao inspecao) { this.inspecao = inspecao; }
}