package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.LoteDTO;
import com.inspecao.inspecao_dormentes.model.Fornecedor;
import com.inspecao.inspecao_dormentes.model.Lote;
import com.inspecao.inspecao_dormentes.repository.FornecedorRepository;
import com.inspecao.inspecao_dormentes.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoteService {

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Lote> listarTodos() {
        return loteRepository.findAll();
    }

    public Lote buscarPorId(Long id) {
        return loteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado com o ID: " + id));
    }

    public Lote salvar(LoteDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com o ID: " + dto.getFornecedorId()));

        Lote lote = new Lote();
        lote.setCodigoLote(dto.getCodigoLote());
        lote.setDataRecebimento(dto.getDataRecebimento());
        lote.setCriadoEm(LocalDateTime.now());
        lote.setFornecedor(fornecedor);

        return loteRepository.save(lote);
    }
}