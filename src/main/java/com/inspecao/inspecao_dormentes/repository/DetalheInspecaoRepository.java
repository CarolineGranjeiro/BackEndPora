package com.inspecao.inspecao_dormentes.repository;

import com.inspecao.inspecao_dormentes.model.DetalheInspecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalheInspecaoRepository extends JpaRepository<DetalheInspecao, Integer> {
    List<DetalheInspecao> findByInspecao_IdOrderByIdAsc(Integer inspecaoId);
    boolean existsByInspecao_IdAndDefeito_Id(Integer inspecaoId, Integer defeitoId);
}