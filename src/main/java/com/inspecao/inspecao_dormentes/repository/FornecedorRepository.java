package com.inspecao.inspecao_dormentes.repository;

import com.inspecao.inspecao_dormentes.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

}
