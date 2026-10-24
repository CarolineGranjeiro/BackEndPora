package com.inspecao.inspecao_dormentes.repository;

import com.inspecao.inspecao_dormentes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
    Optional<Usuario> findByTokenRecuperacao(String tokenRecuperacao);
}