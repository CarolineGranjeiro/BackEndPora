
package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.FornecedorRequestDTO;
import com.inspecao.inspecao_dormentes.dto.FornecedorResponseDTO;
import com.inspecao.inspecao_dormentes.model.Fornecedor;
import com.inspecao.inspecao_dormentes.repository.FornecedorRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Transactional
    public FornecedorResponseDTO criar(FornecedorRequestDTO dto) {

        String nome = dto.nome().trim();
        String cnpj = dto.cnpj().trim();

        String contato = dto.contato() == null
                || dto.contato().isBlank()
                ? null
                : dto.contato().trim();

        if (fornecedorRepository.findByCnpj(cnpj).isPresent()) {
            throw new IllegalArgumentException(
                    "Já existe um fornecedor cadastrado com esse CNPJ."
            );
        }

        Fornecedor fornecedor = new Fornecedor(
                nome,
                cnpj,
                contato
        );

        Fornecedor salvo = fornecedorRepository.save(fornecedor);

        return new FornecedorResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<FornecedorResponseDTO> listarTodos() {

        return fornecedorRepository
                .findAll(Sort.by("nome"))
                .stream()
                .map(FornecedorResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public Fornecedor buscarPorId(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID de fornecedor válido."
            );
        }

        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Fornecedor não encontrado com o ID: " + id
                ));
    }
}
