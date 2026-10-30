package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.ContratoDTO;
import com.inspecao.inspecao_dormentes.model.Contrato;
import com.inspecao.inspecao_dormentes.model.Fornecedor;
import com.inspecao.inspecao_dormentes.repository.ContratoRepository;
import com.inspecao.inspecao_dormentes.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Contrato> listarTodos() {
        return contratoRepository.findAll();
    }

    public Contrato buscarPorId(Long id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado com o ID: " + id));
    }

    public Contrato salvar(ContratoDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com o ID: " + dto.getFornecedorId()));

        Contrato contrato = new Contrato();
        contrato.setNumeroContrato(dto.getNumeroContrato());
        contrato.setDataInicio(dto.getDataInicio());
        contrato.setDataFim(dto.getDataFim());
        contrato.setCriadoEm(LocalDateTime.now());
        contrato.setFornecedor(fornecedor);

        return contratoRepository.save(contrato);
    }
}