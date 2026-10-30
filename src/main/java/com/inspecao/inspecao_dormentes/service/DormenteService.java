package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.DormenteRequestDTO;
import com.inspecao.inspecao_dormentes.model.Contrato;
import com.inspecao.inspecao_dormentes.model.Dormente;
import com.inspecao.inspecao_dormentes.model.Lote;
import com.inspecao.inspecao_dormentes.repository.ContratoRepository;
import com.inspecao.inspecao_dormentes.repository.DormenteRepository;
import com.inspecao.inspecao_dormentes.repository.LoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormenteService {

    private final DormenteRepository dormenteRepository;
    private final LoteRepository loteRepository;
    private final ContratoRepository contratoRepository;

    public DormenteService(DormenteRepository dormenteRepository, LoteRepository loteRepository, ContratoRepository contratoRepository) {
        this.dormenteRepository = dormenteRepository;
        this.loteRepository = loteRepository;
        this.contratoRepository = contratoRepository;
    }

    public Dormente criar(DormenteRequestDTO dto) {
        Lote lote = loteRepository.findById(dto.getLoteId())
                .orElseThrow(() -> new RuntimeException("Lote não encontrado com o ID: " + dto.getLoteId()));

        Contrato contrato = contratoRepository.findById(dto.getContratoId())
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado com o ID: " + dto.getContratoId()));

        Dormente dormente = new Dormente(
                dto.getNumeroSerie(),
                dto.getQrCode(),
                lote,
                contrato,
                dto.getTipoMadeira(),
                dto.getClasseMadeira(),
                dto.getComprimento(),
                dto.getStatus()
        );

        return dormenteRepository.save(dormente);
    }

    public List<Dormente> listarTodos() {
        return dormenteRepository.findAll();
    }
    public Dormente buscarPorQrCode(String qrCode) {
        return dormenteRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Dormente não encontrado para o QR Code: " + qrCode));
    }
}