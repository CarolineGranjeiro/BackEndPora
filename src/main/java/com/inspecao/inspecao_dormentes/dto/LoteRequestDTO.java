package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class LoteRequestDTO {

    @NotBlank(message = "O código do lote é obrigatório.")
    @Size(max = 50, message = "O código do lote deve ter no máximo 50 caracteres.")
    private String codigoLote;

    @NotNull(message = "O ID do fornecedor é obrigatório.")
    private Integer fornecedorId;

    private LocalDate dataRecebimento;

    public LoteRequestDTO() {
    }

    public String getCodigoLote() { return codigoLote; }
    public void setCodigoLote(String codigoLote) { this.codigoLote = codigoLote; }

    public Integer getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Integer fornecedorId) { this.fornecedorId = fornecedorId; }

    public LocalDate getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(LocalDate dataRecebimento) { this.dataRecebimento = dataRecebimento; }
}