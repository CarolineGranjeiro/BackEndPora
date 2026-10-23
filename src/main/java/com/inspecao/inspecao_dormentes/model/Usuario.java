package com.inspecao.inspecao_dormentes.model;

import com.inspecao.inspecao_dormentes.enums.PerfilUsuario;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "senha_embaralhada", nullable = false)
    private String senhaEmbaralhada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilUsuario perfil;

    @Column(name = "token_de_recuperacao")
    private String tokenDeRecuperacao;

    @Column(name = "expiracao_token")
    private LocalDateTime expiracaoToken;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senhaEmbaralhada, PerfilUsuario perfil) {
        this.nome = nome;
        this.email = email;
        this.senhaEmbaralhada = senhaEmbaralhada;
        this.perfil = perfil;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaEmbaralhada() {
        return senhaEmbaralhada;
    }

    public void setSenhaEmbaralhada(String senhaEmbaralhada) {
        this.senhaEmbaralhada = senhaEmbaralhada;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public String getTokenDeRecuperacao() {
        return tokenDeRecuperacao;
    }

    public void setTokenDeRecuperacao(String tokenDeRecuperacao) {
        this.tokenDeRecuperacao = tokenDeRecuperacao;
    }

    public LocalDateTime getExpiracaoToken() {
        return expiracaoToken;
    }

    public void setExpiracaoToken(LocalDateTime expiracaoToken) {
        this.expiracaoToken = expiracaoToken;
    }
}