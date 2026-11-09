package com.inspecao.inspecao_dormentes.repository;

import com.inspecao.inspecao_dormentes.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Integer> {
    List<Relatorio> findByUsuarioIdOrderByCriadoEmDesc(Integer usuarioId);
    List<Relatorio> findByStatus(String status);
}