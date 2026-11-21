
package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.LoteRequestDTO;
import com.inspecao.inspecao_dormentes.dto.LoteResponseDTO;
import com.inspecao.inspecao_dormentes.model.Contrato;
import com.inspecao.inspecao_dormentes.model.Fornecedor;
import com.inspecao.inspecao_dormentes.model.Lote;
import com.inspecao.inspecao_dormentes.repository.ContratoRepository;
import com.inspecao.inspecao_dormentes.repository.LoteRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoteService {

    private final LoteRepository loteRepository;
    private final FornecedorService fornecedorService;
    private final ContratoRepository contratoRepository;

    public LoteService(
            LoteRepository loteRepository,
            FornecedorService fornecedorService,
            ContratoRepository contratoRepository
    ) {
        this.loteRepository = loteRepository;
        this.fornecedorService = fornecedorService;
        this.contratoRepository = contratoRepository;
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

        Contrato contrato = contratoRepository.findById(dto.contratoId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Contrato não encontrado."
                ));

        if (!contrato.getFornecedor().getId().equals(fornecedor.getId())) {
            throw new IllegalArgumentException(
                    "O contrato informado não pertence ao fornecedor selecionado."
            );
        }

        Lote lote = new Lote(
                codigoLote,
                fornecedor,
                dto.dataRecebimento()
        );

        lote.setContrato(contrato);

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
