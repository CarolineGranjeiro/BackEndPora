package com.inspecao.inspecao_dormentes.repository;

import com.inspecao.inspecao_dormentes.model.Inspecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspecaoRepository extends JpaRepository<Inspecao, Integer> {
}