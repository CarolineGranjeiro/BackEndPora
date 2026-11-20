
package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.LoteRequestDTO;
import com.inspecao.inspecao_dormentes.dto.LoteResponseDTO;
import com.inspecao.inspecao_dormentes.model.Fornecedor;
import com.inspecao.inspecao_dormentes.model.Lote;
import com.inspecao.inspecao_dormentes.repository.LoteRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoteService {

    private final LoteRepository loteRepository;
    private final FornecedorService fornecedorService;

    public LoteService(
            LoteRepository loteRepository,
            FornecedorService fornecedorService) {

        this.loteRepository = loteRepository;
        this.fornecedorService = fornecedorService;
    }

    @Transactional
    public LoteResponseDTO criar(LoteRequestDTO dto) {

        String codigoLote = dto.codigoLote().trim();

        if (loteRepository.findByCodigoLote(codigoLote).isPresent()) {
            throw new IllegalArgumentException(
                    "Já existe um lote cadastrado com esse código."
            );
        }

        Fornecedor fornecedor = fornecedorService.buscarPorId(
                dto.fornecedorId()
        );

        Lote lote = new Lote(
                codigoLote,
                fornecedor,
                dto.dataRecebimento()
        );

        Lote salvo = loteRepository.save(lote);

        return new LoteResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<LoteResponseDTO> listarTodos() {

        return loteRepository
                .findAll(Sort.by("codigoLote"))
                .stream()
                .map(LoteResponseDTO::new)
                .toList();
    }
}
