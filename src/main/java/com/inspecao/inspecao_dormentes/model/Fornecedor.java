package com.inspecao.inspecao_dormentes.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "cnpj", nullable = false, unique = true, length = 20)
    private String cnpj;

    @Column(name = "contato",length = 100)
    private String contato;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private List<Contrato> contratos;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private List<Lote> lotes;

    public Fornecedor() {
    }

    public Fornecedor(String nome, String cnpj, String contato) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.contato = contato;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }
    public List<Contrato> getContratos() {
        return contratos;
    }
    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
    public List<Lote> getLotes() {
        return lotes;
    }
    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }
}