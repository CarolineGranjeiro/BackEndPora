package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.DetalheInspecaoRequestDTO;
import com.inspecao.inspecao_dormentes.dto.DetalheInspecaoResponseDTO;
import com.inspecao.inspecao_dormentes.model.CatalogoDefeito;
import com.inspecao.inspecao_dormentes.model.DetalheInspecao;
import com.inspecao.inspecao_dormentes.model.Inspecao;
import com.inspecao.inspecao_dormentes.repository.CatalogoDefeitoRepository;
import com.inspecao.inspecao_dormentes.repository.DetalheInspecaoRepository;
import com.inspecao.inspecao_dormentes.repository.InspecaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DetalheInspecaoService {

    private final DetalheInspecaoRepository detalheRepository;
    private final CatalogoDefeitoRepository defeitoRepository;
    private final InspecaoRepository inspecaoRepository;

    public DetalheInspecaoService(
            DetalheInspecaoRepository detalheRepository,
            CatalogoDefeitoRepository defeitoRepository,
            InspecaoRepository inspecaoRepository) {

        this.detalheRepository = detalheRepository;
        this.defeitoRepository = defeitoRepository;
        this.inspecaoRepository = inspecaoRepository;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<DetalheInspecaoResponseDTO> cadastrarDetalhes(
            Inspecao inspecao,
            List<DetalheInspecaoRequestDTO> itens) {

        if (itens == null) {
            throw new IllegalArgumentException(
                    "Informe a lista de defeitos, mesmo que vazia."
            );
        }

        Set<Integer> idsInformados = new HashSet<>();
        List<DetalheInspecao> detalhes = new ArrayList<>();

        for (DetalheInspecaoRequestDTO item : itens) {
            if (item == null
                    || item.defeitoId() == null
                    || item.defeitoId() <= 0) {

                throw new IllegalArgumentException(
                        "Informe um ID de defeito válido."
                );
            }

            if (!idsInformados.add(item.defeitoId())) {
                throw new IllegalArgumentException(
                        "O mesmo defeito não pode ser repetido na inspeção."
                );
            }

            CatalogoDefeito defeito = defeitoRepository
                    .findById(item.defeitoId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Defeito não encontrado com o ID: " + item.defeitoId()
                    ));

            if (detalheRepository.existsByInspecao_IdAndDefeito_Id(
                    inspecao.getId(), defeito.getId())) {

                throw new IllegalArgumentException(
                        "Este defeito já está associado à inspeção."
                );
            }

            String observacao = item.observacao();

            if (observacao != null) {
                observacao = observacao.trim();

                if (observacao.length() > 255) {
                    throw new IllegalArgumentException(
                            "A observação do defeito deve ter no máximo 255 caracteres."
                    );
                }
            }

            if ("OUTROS".equalsIgnoreCase(defeito.getCodigoDefeito())
                    && (inspecao.getObservacoes() == null
                    || inspecao.getObservacoes().isBlank())) {

                throw new IllegalArgumentException(
                        "Preencha as observações da inspeção ao selecionar Outros."
                );
            }

            detalhes.add(new DetalheInspecao(
                    inspecao,
                    defeito,
                    observacao
            ));
        }

        return detalheRepository.saveAll(detalhes)
                .stream()
                .map(DetalheInspecaoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DetalheInspecaoResponseDTO> listarPorInspecao(
            Integer inspecaoId) {

        if (inspecaoId == null || inspecaoId <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID de inspeção válido."
            );
        }

        if (!inspecaoRepository.existsById(inspecaoId)) {
            throw new IllegalArgumentException(
                    "Inspeção não encontrada com o ID: " + inspecaoId
            );
        }

        return detalheRepository
                .findByInspecao_IdOrderByIdAsc(inspecaoId)
                .stream()
                .map(DetalheInspecaoResponseDTO::new)
                .toList();
    }
}