package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class ContratoRequestDTO {

    @NotBlank(message = "O número do contrato é obrigatório.")
    @Size(max = 50, message = "O número do contrato deve ter no máximo 50 caracteres.")
    private String numeroContrato;

    @NotNull(message = "O ID do fornecedor é obrigatório.")
    private Integer fornecedorId;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    public ContratoRequestDTO() {
    }

    public String getNumeroContrato() { return numeroContrato; }
    public void setNumeroContrato(String numeroContrato) { this.numeroContrato = numeroContrato; }

    public Integer getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Integer fornecedorId) { this.fornecedorId = fornecedorId; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
}