package com.inspecao.inspecao_dormentes.repository;

import com.inspecao.inspecao_dormentes.model.Dormente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DormenteRepository extends JpaRepository<Dormente, Long> {
    Optional<Dormente> findByQrCode(String qrCode);
}