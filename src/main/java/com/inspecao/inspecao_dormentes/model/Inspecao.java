package com.inspecao.inspecao_dormentes.model;

import java.time.LocalDate;

public class Inspecao {
    private Long id;
    private Long dormenteId;
    private Long usuarioId;
    private LocalDate dataInspecao;
    private String resultadoGeral;


    // Construtor

    public Inspecao() {

    }

    public Inspecao(Long dormenteId, Long usuarioId, LocalDate dataInspecao, String resultadoGeral) {
        this.dormenteId = dormenteId;
        this.usuarioId = usuarioId;
        this.dataInspecao = dataInspecao;
        this.resultadoGeral = resultadoGeral;

    }




    //getter e setters
    public LocalDate getDataInspecao() {
        return dataInspecao;
    }

    public void setDataInspecao(LocalDate dataInspecao) {
        this.dataInspecao = dataInspecao;
    }

    public Long getDormenteId() {
        return dormenteId;
    }

    public void setDormenteId(Long dormenteId) {
        this.dormenteId = dormenteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResultadoGeral() {
        return resultadoGeral;
    }

    public void setResultadoGeral(String resultadoGeral) {
        this.resultadoGeral = resultadoGeral;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
