package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.RelatorioRequestDTO;
import com.inspecao.inspecao_dormentes.dto.RelatorioResponseDTO;
import com.inspecao.inspecao_dormentes.model.Inspecao;
import com.inspecao.inspecao_dormentes.model.Relatorio;
import com.inspecao.inspecao_dormentes.model.RelatorioInspecao;
import com.inspecao.inspecao_dormentes.model.Usuario;
import com.inspecao.inspecao_dormentes.repository.InspecaoRepository;
import com.inspecao.inspecao_dormentes.repository.RelatorioInspecaoRepository;
import com.inspecao.inspecao_dormentes.repository.RelatorioRepository;
import com.inspecao.inspecao_dormentes.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final RelatorioInspecaoRepository relatorioInspecaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final InspecaoRepository inspecaoRepository;

    public RelatorioService(
            RelatorioRepository relatorioRepository,
            RelatorioInspecaoRepository relatorioInspecaoRepository,
            UsuarioRepository usuarioRepository,
            InspecaoRepository inspecaoRepository
    ) {
        this.relatorioRepository = relatorioRepository;
        this.relatorioInspecaoRepository = relatorioInspecaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.inspecaoRepository = inspecaoRepository;
    }

    @Transactional(readOnly = true)
    public List<RelatorioResponseDTO> listarTodos() {
        return relatorioRepository.findAll()
                .stream()
                .map(RelatorioResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public RelatorioResponseDTO buscarPorId(Integer id) {
        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Relatório não encontrado para o ID: " + id));
        return new RelatorioResponseDTO(relatorio);
    }

    @Transactional
    public RelatorioResponseDTO criar(RelatorioRequestDTO dto) {
        if (dto.dataInicio() != null && dto.dataFim() != null && dto.dataFim().isBefore(dto.dataInicio())) {
            throw new IllegalArgumentException("A data final não pode ser anterior à data inicial.");
        }

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID: " + dto.usuarioId()));

        Relatorio relatorio = new Relatorio(
                usuario,
                dto.tipo(),
                dto.formato(),
                dto.dataInicio(),
                dto.dataFim(),
                dto.material(),
                dto.localizacaoId(),
                dto.graficosSelecionados()
        );

        Relatorio salvo = relatorioRepository.save(relatorio);

        if (dto.inspecoesIds() != null && !dto.inspecoesIds().isEmpty()) {
            for (Integer inspecaoId : dto.inspecoesIds()) {
                Inspecao inspecao = inspecaoRepository.findById(inspecaoId)
                        .orElseThrow(() -> new IllegalArgumentException("Inspeção não encontrada para o ID: " + inspecaoId));

                if (!relatorioInspecaoRepository.existsByRelatorioIdAndInspecaoId(salvo.getId(), inspecaoId)) {
                    relatorioInspecaoRepository.save(new RelatorioInspecao(salvo, inspecao));
                }
            }
        }

        return new RelatorioResponseDTO(salvo);
    }

    @Transactional
    public RelatorioResponseDTO atualizarStatus(Integer id, String novoStatus) {
        if (!List.of("PROCESSANDO", "CONCLUIDO", "FALHOU").contains(novoStatus)) {
            throw new IllegalArgumentException("Status inválido. Use PROCESSANDO, CONCLUIDO ou FALHOU.");
        }

        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Relatório não encontrado para o ID: " + id));

        relatorio.setStatus(novoStatus);
        if ("CONCLUIDO".equals(novoStatus) || "FALHOU".equals(novoStatus)) {
            relatorio.setConcluidoEm(LocalDateTime.now());
        }

        return new RelatorioResponseDTO(relatorioRepository.save(relatorio));
    }
}