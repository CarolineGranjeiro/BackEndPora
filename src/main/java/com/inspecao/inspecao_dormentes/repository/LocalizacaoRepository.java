package com.inspecao.inspecao_dormentes.repository;

import com.inspecao.inspecao_dormentes.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizacaoRepository
        extends JpaRepository<Localizacao, Integer> {

    boolean existsByPatioAndSetorAndPosicao(
            String patio,
            String setor,
            String posicao
    );
}