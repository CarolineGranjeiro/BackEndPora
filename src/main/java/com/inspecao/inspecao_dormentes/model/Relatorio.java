package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "relatorios")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "tipo", nullable = false, length = 30)
    private String tipo; // 'ACOMPANHAMENTO' ou 'INVENTARIO'

    @Column(name = "formato", nullable = false, length = 10)
    private String formato; // 'PDF', 'XLSX' ou 'CSV'

    @Column(name = "status", nullable = false, length = 20)
    private String status = "PROCESSANDO"; // 'PROCESSANDO', 'CONCLUIDO' ou 'FALHOU'

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "material", length = 50)
    private String material;

    // Mantido como Integer para não depender da classe Localizacao da sua amiga
    @Column(name = "localizacao_id")
    private Integer localizacaoId;

    @Column(name = "graficos_selecionados", columnDefinition = "JSON")
    private String graficosSelecionados;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "concluido_em")
    private LocalDateTime concluidoEm;

    public Relatorio() {}

    public Relatorio(Usuario usuario, String tipo, String formato, LocalDate dataInicio,
                     LocalDate dataFim, String material, Integer localizacaoId, String graficosSelecionados) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.formato = formato;
        this.status = "PROCESSANDO";
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.material = material;
        this.localizacaoId = localizacaoId;
        this.graficosSelecionados = graficosSelecionados;
    }

    @PrePersist
    protected void onCreate() {
        if (this.criadoEm == null) {
            this.criadoEm = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = "PROCESSANDO";
        }
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public Integer getLocalizacaoId() { return localizacaoId; }
    public void setLocalizacaoId(Integer localizacaoId) { this.localizacaoId = localizacaoId; }

    public String getGraficosSelecionados() { return graficosSelecionados; }
    public void setGraficosSelecionados(String graficosSelecionados) { this.graficosSelecionados = graficosSelecionados; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public LocalDateTime getConcluidoEm() { return concluidoEm; }
    public void setConcluidoEm(LocalDateTime concluidoEm) { this.concluidoEm = concluidoEm; }
}