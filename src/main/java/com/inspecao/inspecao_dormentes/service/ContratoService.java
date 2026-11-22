
package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.ContratoRequestDTO;
import com.inspecao.inspecao_dormentes.dto.ContratoResponseDTO;
import com.inspecao.inspecao_dormentes.exception.DuplicateResourceException;
import com.inspecao.inspecao_dormentes.model.Contrato;
import com.inspecao.inspecao_dormentes.model.Fornecedor;
import com.inspecao.inspecao_dormentes.repository.ContratoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final FornecedorService fornecedorService;

    public ContratoService(
            ContratoRepository contratoRepository,
            FornecedorService fornecedorService) {

        this.contratoRepository = contratoRepository;
        this.fornecedorService = fornecedorService;
    }

    @Transactional
    public ContratoResponseDTO criar(ContratoRequestDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException(
                    "Informe os dados do contrato."
            );
        }

        if (dto.numeroContrato() == null
                || dto.numeroContrato().isBlank()) {

            throw new IllegalArgumentException(
                    "O número do contrato é obrigatório."
            );
        }

        if (dto.fornecedorId() == null || dto.fornecedorId() <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID de fornecedor válido."
            );
        }

        String numeroContrato = dto.numeroContrato().trim();

        if (contratoRepository.findByNumeroContrato(numeroContrato)
                .isPresent()) {

            throw new DuplicateResourceException(
                    "Já existe um contrato cadastrado com esse número."
            );
        }

        if (dto.dataInicio() != null
                && dto.dataFim() != null
                && dto.dataFim().isBefore(dto.dataInicio())) {

            throw new IllegalArgumentException(
                    "A data de término não pode ser anterior à data de início."
            );
        }

        Fornecedor fornecedor = fornecedorService.buscarPorId(
                dto.fornecedorId()
        );

        Contrato contrato = new Contrato(
                numeroContrato,
                fornecedor,
                dto.dataInicio(),
                dto.dataFim()
        );

        Contrato salvo = contratoRepository.save(contrato);

        return new ContratoResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<ContratoResponseDTO> listarTodos() {

        return contratoRepository
                .findAll(Sort.by("numeroContrato"))
                .stream()
                .map(ContratoResponseDTO::new)
                .toList();
    }
}
