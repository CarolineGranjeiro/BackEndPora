package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.FornecedorDTO;
import com.inspecao.inspecao_dormentes.model.Fornecedor;
import com.inspecao.inspecao_dormentes.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class FornecedorService {

        @Autowired
        private FornecedorRepository fornecedorRepository;

        public List<Fornecedor> listarTodos() {
            return fornecedorRepository.findAll();
        }

        public Fornecedor buscarPorId(Long id) {
            return fornecedorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com o ID: " + id));
        }

        public Fornecedor salvar(FornecedorDTO dto) {
            Fornecedor fornecedor = new Fornecedor(dto.getNome(), dto.getCnpj(), dto.getContato());
            return fornecedorRepository.save(fornecedor);
        }
    }

