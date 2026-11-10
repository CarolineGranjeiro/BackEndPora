package com.inspecao.inspecao_dormentes.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity                          // diz ao JPA: esta classe representa uma tabela
@Table(name = "inspecoes")       // nome da tabela no banco (diferente do nome da classe)
public class Inspecao {

    @Id                                                   // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // o MySQL gera o id (AUTO_INCREMENT)
    private int id;

    // Antes: Long dormenteId -> agora guardamos o OBJETO Dormente
    @ManyToOne                                // várias inspeções -> um dormente
    @JoinColumn(name = "dormente_id", nullable = false)  // coluna da FK no banco
    private Dormente dormente;

    // Antes: Long usuarioId -> agora guardamos o OBJETO Usuario
    @ManyToOne                                // várias inspeções -> um usuário
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "data_inspecao")
    private LocalDate dataInspecao;

    // Continua String até confirmarmos se a coluna usa o enum StatusDormente
    @Column(name = "resultado_geral")
    private String resultadoGeral;


    // Construtores

    public Inspecao() {
        // o JPA exige um construtor vazio
    }

    public Inspecao(Dormente dormente, Usuario usuario, LocalDate dataInspecao, String resultadoGeral) {
        this.dormente = dormente;
        this.usuario = usuario;
        this.dataInspecao = dataInspecao;
        this.resultadoGeral = resultadoGeral;
    }


    // Getters e setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDate getDataInspecao() {
        return dataInspecao;
    }

    public void setDataInspecao(LocalDate dataInspecao) {
        this.dataInspecao = dataInspecao;
    }

    public String getResultadoGeral() {
        return resultadoGeral;
    }

    public void setResultadoGeral(String resultadoGeral) {
        this.resultadoGeral = resultadoGeral;
    }
}