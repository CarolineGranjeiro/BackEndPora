package com.inspecao.inspecao_dormentes.repository;

import com.inspecao.inspecao_dormentes.model.CatalogoDefeito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogoDefeitoRepository extends JpaRepository<CatalogoDefeito, Integer> {
    boolean existsByCodigoDefeito(String codigoDefeito);
    Optional<CatalogoDefeito> findByCodigoDefeito(String codigoDefeito);
}