package com.inspecao.inspecao_dormentes.service;


import com.inspecao.inspecao_dormentes.dto.FornecedorRequestDTO;
import com.inspecao.inspecao_dormentes.model.Fornecedor;
import com.inspecao.inspecao_dormentes.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class FornecedorService {

        private final FornecedorRepository fornecedorRepository;

        public FornecedorService(FornecedorRepository fornecedorRepository) {
            this.fornecedorRepository = fornecedorRepository;
        }

        public Fornecedor criar(FornecedorRequestDTO dto) {
            Fornecedor fornecedor = new Fornecedor(dto.getNome(), dto.getCnpj(), dto.getContato());
            return fornecedorRepository.save(fornecedor);
        }

        public List<Fornecedor> listarTodos() {
            return fornecedorRepository.findAll();
        }

        public Fornecedor buscarPorId(Integer id) {
            return fornecedorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com o ID: " + id));
        }
    }