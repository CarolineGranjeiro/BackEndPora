package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.ContratoRequestDTO;
import com.inspecao.inspecao_dormentes.model.Contrato;
import com.inspecao.inspecao_dormentes.model.Fornecedor;
import com.inspecao.inspecao_dormentes.repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final FornecedorService fornecedorService;

    public ContratoService(ContratoRepository contratoRepository, FornecedorService fornecedorService) {
        this.contratoRepository = contratoRepository;
        this.fornecedorService = fornecedorService;
    }

    public Contrato criar(ContratoRequestDTO dto) {
        Fornecedor fornecedor = fornecedorService.buscarPorId(dto.getFornecedorId());

        Contrato contrato = new Contrato(dto.getNumeroContrato(), fornecedor, dto.getDataInicio(), dto.getDataFim());
        return contratoRepository.save(contrato);
    }

    public List<Contrato> listarTodos() {
        return contratoRepository.findAll();
    }
}