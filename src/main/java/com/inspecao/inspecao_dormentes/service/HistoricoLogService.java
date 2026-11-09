package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.HistoricoLogRequestDTO;
import com.inspecao.inspecao_dormentes.dto.HistoricoLogResponseDTO;
import com.inspecao.inspecao_dormentes.model.HistoricoLog;
import com.inspecao.inspecao_dormentes.model.Usuario;
import com.inspecao.inspecao_dormentes.repository.HistoricoLogRepository;
import com.inspecao.inspecao_dormentes.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistoricoLogService {

    private final HistoricoLogRepository historicoLogRepository;
    private final UsuarioRepository usuarioRepository;

    public HistoricoLogService(HistoricoLogRepository historicoLogRepository, UsuarioRepository usuarioRepository) {
        this.historicoLogRepository = historicoLogRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<HistoricoLogResponseDTO> listarTodos() {
        return historicoLogRepository.findAll()
                .stream()
                .map(HistoricoLogResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<HistoricoLogResponseDTO> listarPorUsuario(Integer usuarioId) {
        return historicoLogRepository.findByUsuarioIdOrderByCriadoEmDesc(usuarioId)
                .stream()
                .map(HistoricoLogResponseDTO::new)
                .toList();
    }

    @Transactional
    public HistoricoLogResponseDTO registrar(HistoricoLogRequestDTO dto) {
        if (historicoLogRepository.existsByCodigoAtividade(dto.codigoAtividade())) {
            throw new IllegalArgumentException("Já existe um log com o código de atividade: " + dto.codigoAtividade());
        }

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID: " + dto.usuarioId()));

        HistoricoLog log = new HistoricoLog(
                dto.codigoAtividade(),
                usuario,
                dto.acao(),
                dto.entidade(),
                dto.entidadeId(),
                dto.descricao(),
                dto.dadosAnteriores(),
                dto.dadosNovos()
        );

        return new HistoricoLogResponseDTO(historicoLogRepository.save(log));
    }
}