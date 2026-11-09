package com.inspecao.inspecao_dormentes.repository;

import com.inspecao.inspecao_dormentes.model.HistoricoLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoLogRepository extends JpaRepository<HistoricoLog, Integer> {
    boolean existsByCodigoAtividade(String codigoAtividade);
    List<HistoricoLog> findByUsuarioIdOrderByCriadoEmDesc(Integer usuarioId);
    List<HistoricoLog> findByEntidadeAndEntidadeIdOrderByCriadoEmDesc(String entidade, Integer entidadeId);
}