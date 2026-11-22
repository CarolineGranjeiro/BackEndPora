
package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.LocalizacaoRequestDTO;
import com.inspecao.inspecao_dormentes.dto.LocalizacaoResponseDTO;
import com.inspecao.inspecao_dormentes.exception.DuplicateResourceException;
import com.inspecao.inspecao_dormentes.exception.ResourceNotFoundException;
import com.inspecao.inspecao_dormentes.model.Localizacao;
import com.inspecao.inspecao_dormentes.repository.LocalizacaoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository localizacaoRepository;

    public LocalizacaoService(
            LocalizacaoRepository localizacaoRepository) {

        this.localizacaoRepository = localizacaoRepository;
    }

    @Transactional
    public LocalizacaoResponseDTO cadastrar(LocalizacaoRequestDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException(
                    "Informe os dados da localização."
            );
        }

        if (dto.patio() == null || dto.patio().isBlank()) {
            throw new IllegalArgumentException(
                    "O pátio é obrigatório."
            );
        }

        if (dto.setor() == null || dto.setor().isBlank()) {
            throw new IllegalArgumentException(
                    "O setor é obrigatório."
            );
        }

        if (dto.posicao() == null || dto.posicao().isBlank()) {
            throw new IllegalArgumentException(
                    "A posição é obrigatória."
            );
        }

        String patio = dto.patio().trim();
        String setor = dto.setor().trim();
        String posicao = dto.posicao().trim();

        boolean jaExiste =
                localizacaoRepository.existsByPatioAndSetorAndPosicao(
                        patio,
                        setor,
                        posicao
                );

        if (jaExiste) {
            throw new DuplicateResourceException(
                    "Já existe uma localização com esse pátio, setor e posição."
            );
        }

        String descricao = dto.descricao() == null
                || dto.descricao().isBlank()
                ? null
                : dto.descricao().trim();

        Localizacao localizacao = new Localizacao(
                patio,
                setor,
                posicao,
                descricao
        );

        Localizacao salva = localizacaoRepository.save(localizacao);

        return new LocalizacaoResponseDTO(salva);
    }

    @Transactional(readOnly = true)
    public List<LocalizacaoResponseDTO> listarTodos() {

        return localizacaoRepository
                .findAll(Sort.by("patio", "setor", "posicao"))
                .stream()
                .map(LocalizacaoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public LocalizacaoResponseDTO buscarPorId(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID de localização válido."
            );
        }

        Localizacao localizacao = localizacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Localização não encontrada com o ID: " + id
                ));

        return new LocalizacaoResponseDTO(localizacao);
    }
}
