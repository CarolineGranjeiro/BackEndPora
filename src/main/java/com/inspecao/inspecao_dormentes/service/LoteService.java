package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.LoteRequestDTO;
import com.inspecao.inspecao_dormentes.model.Fornecedor;
import com.inspecao.inspecao_dormentes.model.Lote;
import com.inspecao.inspecao_dormentes.repository.LoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoteService {

    private final LoteRepository loteRepository;
    private final FornecedorService fornecedorService;

    public LoteService(LoteRepository loteRepository, FornecedorService fornecedorService) {
        this.loteRepository = loteRepository;
        this.fornecedorService = fornecedorService;
    }

    public Lote criar(LoteRequestDTO dto) {
        Fornecedor fornecedor = fornecedorService.buscarPorId(dto.getFornecedorId());

        Lote lote = new Lote(dto.getCodigoLote(), fornecedor, dto.getDataRecebimento());
        return loteRepository.save(lote);
    }

    public List<Lote> listarTodos() {
        return loteRepository.findAll();
    }
}