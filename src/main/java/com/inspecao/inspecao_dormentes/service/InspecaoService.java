package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.DetalheInspecaoResponseDTO;
import com.inspecao.inspecao_dormentes.dto.InspecaoRequestDTO;
import com.inspecao.inspecao_dormentes.dto.InspecaoResponseDTO;
import com.inspecao.inspecao_dormentes.model.Dormente;
import com.inspecao.inspecao_dormentes.model.Inspecao;
import com.inspecao.inspecao_dormentes.model.Usuario;
import com.inspecao.inspecao_dormentes.repository.DormenteRepository;
import com.inspecao.inspecao_dormentes.repository.InspecaoRepository;
import com.inspecao.inspecao_dormentes.repository.UsuarioRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InspecaoService {

    private final InspecaoRepository inspecaoRepository;
    private final DormenteRepository dormenteRepository;
    private final UsuarioRepository usuarioRepository;
    private final DetalheInspecaoService detalheInspecaoService;

    public InspecaoService(
            InspecaoRepository inspecaoRepository,
            DormenteRepository dormenteRepository,
            UsuarioRepository usuarioRepository,
            DetalheInspecaoService detalheInspecaoService) {

        this.inspecaoRepository = inspecaoRepository;
        this.dormenteRepository = dormenteRepository;
        this.usuarioRepository = usuarioRepository;
        this.detalheInspecaoService = detalheInspecaoService;
    }

    @Transactional
    public InspecaoResponseDTO cadastrar(
            InspecaoRequestDTO dto,
            Integer usuarioId) {

        if (usuarioId == null) {
            throw new IllegalArgumentException(
                    "O responsável pela inspeção é obrigatório."
            );
        }

        if (dto.quantidadeObservada() == null
                || dto.quantidadeObservada() <= 0) {

            throw new IllegalArgumentException(
                    "A quantidade observada deve ser maior que zero."
            );
        }

        Dormente dormente = dormenteRepository
                .findById(dto.dormenteId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Dormente não encontrado com o ID: " + dto.dormenteId()
                ));

        Usuario usuario = usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuário responsável não encontrado."
                ));

        Inspecao inspecao = new Inspecao(
                dormente,
                usuario,
                dto.dataInspecao(),
                dto.quantidadeObservada(),
                dto.status().trim(),
                dto.observacoes()
        );

        Inspecao salva = inspecaoRepository.save(inspecao);

        List<DetalheInspecaoResponseDTO> detalhes =
                detalheInspecaoService.cadastrarDetalhes(
                        salva,
                        dto.detalhes()
                );

        return montarResposta(salva, detalhes);
    }

    @Transactional(readOnly = true)
    public List<InspecaoResponseDTO> listarTodos() {
        Sort ordenacao = Sort.by(
                Sort.Order.desc("dataInspecao"),
                Sort.Order.desc("id")
        );

        return inspecaoRepository.findAll(ordenacao)
                .stream()
                .map(inspecao -> montarResposta(
                        inspecao,
                        detalheInspecaoService.listarPorInspecao(inspecao.getId())
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public InspecaoResponseDTO buscarPorId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID de inspeção válido."
            );
        }

        Inspecao inspecao = inspecaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Inspeção não encontrada com o ID: " + id
                ));

        List<DetalheInspecaoResponseDTO> detalhes =
                detalheInspecaoService.listarPorInspecao(id);

        return montarResposta(inspecao, detalhes);
    }

    private InspecaoResponseDTO montarResposta(
            Inspecao inspecao,
            List<DetalheInspecaoResponseDTO> detalhes) {

        return new InspecaoResponseDTO(
                inspecao.getId(),
                inspecao.getDormente().getId(),
                inspecao.getUsuario().getId(),
                inspecao.getDataInspecao(),
                inspecao.getQuantidadeObservada(),
                inspecao.getStatus(),
                inspecao.getObservacoes(),
                inspecao.getCriadoEm(),
                detalhes
        );
    }
}