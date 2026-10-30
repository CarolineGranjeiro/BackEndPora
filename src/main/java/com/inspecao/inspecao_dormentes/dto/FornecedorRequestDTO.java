package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FornecedorRequestDTO {

    @NotBlank(message = "O nome do fornecedor é obrigatório.")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
    private String nome;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @Size(max = 20, message = "O CNPJ deve ter no máximo 20 caracteres.")
    private String cnpj;

    @Size(max = 100, message = "O contato deve ter no máximo 100 caracteres.")
    private String contato;

    public FornecedorRequestDTO() {
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
}
