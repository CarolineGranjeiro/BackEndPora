package com.inspecao.inspecao_dormentes.repository;

import com.inspecao.inspecao_dormentes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Avisa o Spring que este componente acede à base de dados
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Procura um utilizador pelo e-mail
    Optional<Usuario> findByEmail(String email);

    // Verifica se já existe alguém registado com este e-mail
    boolean existsByEmail(String email);
}