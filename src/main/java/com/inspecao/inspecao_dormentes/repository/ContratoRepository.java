package com.inspecao.inspecao_dormentes.repository;

import com.inspecao.inspecao_dormentes.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
}