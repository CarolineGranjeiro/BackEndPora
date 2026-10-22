package com.inspecao.inspecao_dormentes.model;
import com.inspecao.inspecao_dormentes.enums.PerfilUsuario;

import java.time.LocalDateTime;

public class Usuario {
    private String nome;
    private String email;
    private Long id;
    private String senhaEmbaralhada;
    private PerfilUsuario perfil;
    private String tokenDeRecuperacao;
    private LocalDateTime expiracaoToken;

    //construtor

    public Usuario(){

    }

    public Usuario(String nome,  String email, String senhaEmbaralhada, PerfilUsuario perfil) {
        this.nome = nome;
        this.email = email;
        this.senhaEmbaralhada = senhaEmbaralhada;
        this.perfil = perfil;

    }

//getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getExpiracaoToken() {
        return expiracaoToken;
    }

    public void setExpiracaoToken(LocalDateTime expiracaoToken) {
        this.expiracaoToken = expiracaoToken;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public String getSenhaEmbaralhada() {
        return senhaEmbaralhada;
    }

    public void setSenhaEmbaralhada(String senhaEmbaralhada) {
        this.senhaEmbaralhada = senhaEmbaralhada;
    }

    public String getTokenDeRecuperacao() {
        return tokenDeRecuperacao;
    }

    public void setTokenDeRecuperacao(String tokenDeRecuperacao) {
        this.tokenDeRecuperacao = tokenDeRecuperacao;
    }
}
