package com.inspecao.inspecao_dormentes.repository;

import com.inspecao.inspecao_dormentes.model.RelatorioInspecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatorioInspecaoRepository extends JpaRepository<RelatorioInspecao, Integer> {
    List<RelatorioInspecao> findByRelatorioId(Integer relatorioId);
    boolean existsByRelatorioIdAndInspecaoId(Integer relatorioId, Integer inspecaoId);
}