package com.inspecao.inspecao_dormentes.dto;

import java.time.LocalDate;

public class LoteDTO {

    private Long id;
    private String codigoLote;
    private LocalDate dataRecebimento;
    private Long fornecedorId;

    public LoteDTO() {
    }

    public LoteDTO(Long id, String codigoLote, LocalDate dataRecebimento, Long fornecedorId) {
        this.id = id;
        this.codigoLote = codigoLote;
        this.dataRecebimento = dataRecebimento;
        this.fornecedorId = fornecedorId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoLote() { return codigoLote; }
    public void setCodigoLote(String codigoLote) { this.codigoLote = codigoLote; }

    public LocalDate getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(LocalDate dataRecebimento) { this.dataRecebimento = dataRecebimento; }

    public Long getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Long fornecedorId) { this.fornecedorId = fornecedorId; }
}