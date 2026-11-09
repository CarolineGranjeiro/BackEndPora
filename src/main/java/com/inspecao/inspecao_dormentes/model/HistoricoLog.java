package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_logs")
public class HistoricoLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_atividade", nullable = false, unique = true, length = 50)
    private String codigoAtividade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "acao", nullable = false, length = 50)
    private String acao;

    @Column(name = "entidade", nullable = false, length = 50)
    private String entidade;

    @Column(name = "entidade_id", nullable = false)
    private Integer entidadeId;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "dados_anteriores", columnDefinition = "JSON")
    private String dadosAnteriores;

    @Column(name = "dados_novos", columnDefinition = "JSON")
    private String dadosNovos;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public HistoricoLog() {}

    public HistoricoLog(String codigoAtividade, Usuario usuario, String acao, String entidade,
                        Integer entidadeId, String descricao, String dadosAnteriores, String dadosNovos) {
        this.codigoAtividade = codigoAtividade;
        this.usuario = usuario;
        this.acao = acao;
        this.entidade = entidade;
        this.entidadeId = entidadeId;
        this.descricao = descricao;
        this.dadosAnteriores = dadosAnteriores;
        this.dadosNovos = dadosNovos;
    }

    @PrePersist
    protected void onCreate() {
        if (this.criadoEm == null) {
            this.criadoEm = LocalDateTime.now();
        }
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCodigoAtividade() { return codigoAtividade; }
    public void setCodigoAtividade(String codigoAtividade) { this.codigoAtividade = codigoAtividade; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getAcao() { return acao; }
    public void setAcao(String acao) { this.acao = acao; }

    public String getEntidade() { return entidade; }
    public void setEntidade(String entidade) { this.entidade = entidade; }

    public Integer getEntidadeId() { return entidadeId; }
    public void setEntidadeId(Integer entidadeId) { this.entidadeId = entidadeId; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDadosAnteriores() { return dadosAnteriores; }
    public void setDadosAnteriores(String dadosAnteriores) { this.dadosAnteriores = dadosAnteriores; }

    public String getDadosNovos() { return dadosNovos; }
    public void setDadosNovos(String dadosNovos) { this.dadosNovos = dadosNovos; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}