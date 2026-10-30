package com.inspecao.inspecao_dormentes.dto;

import java.time.LocalDate;

public class ContratoDTO {
    private Long id;
    private String numeroContrato;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long fornecedorId;

    public ContratoDTO(){
    }

    public ContratoDTO(Long id, String numeroContrato, LocalDate dataInicio, LocalDate dataFim, Long fornecedorId) {
        this.id = id;
        this.numeroContrato = numeroContrato;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.fornecedorId = fornecedorId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }
    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }
    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }
}
