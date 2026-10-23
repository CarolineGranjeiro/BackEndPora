package com.inspecao.inspecao_dormentes.model;

import com.inspecao.inspecao_dormentes.enums.PerfilUsuario;
import jakarta.persistence.*; // Conecta a classe com o banco
import java.time.LocalDateTime;

@Entity // Vira tabela no banco
@Table(name = "usuarios") // Nome da tabela
public class Usuario {

    @Id // Chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento (1, 2, 3...)
    private Long id;

    @Column(nullable = false, length = 150) // Campo obrigatório
    private String nome;

    @Column(nullable = false, unique = true, length = 150) // Obrigatório e não repete
    private String email;

    @Column(name = "senha_embaralhada", nullable = false)
    private String senhaEmbaralhada;

    @Enumerated(EnumType.STRING) // Salva o texto do enum (ex: "ADMIN")
    @Column(nullable = false)
    private PerfilUsuario perfil;

    @Column(name = "token_de_recuperacao")
    private String tokenDeRecuperacao;

    @Column(name = "expiracao_token")
    private LocalDateTime expiracaoToken;

    // Construtor vazio (obrigatório para o JPA)
    public Usuario() {
    }

    public Usuario(String nome, String email, String senhaEmbaralhada, PerfilUsuario perfil) {
        this.nome = nome;
        this.email = email;
        this.senhaEmbaralhada = senhaEmbaralhada;
        this.perfil = perfil;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    /*
     * Versão inicial feita pela Carol.
     * Comentada para evitar duplicação, pois os campos subiram com as anotações do banco.
     *
     * private String nome;
     * private String email;
     * private Long id;
     * private String senhaEmbaralhada;
     * private PerfilUsuario perfil;
     * private String tokenDeRecuperacao;
     * private LocalDateTime expiracaoToken;
     */
}