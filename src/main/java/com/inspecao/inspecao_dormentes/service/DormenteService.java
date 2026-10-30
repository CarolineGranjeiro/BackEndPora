package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.DormenteDTO;
import com.inspecao.inspecao_dormentes.model.Contrato;
import com.inspecao.inspecao_dormentes.model.Dormente;
import com.inspecao.inspecao_dormentes.model.Lote;
import com.inspecao.inspecao_dormentes.repository.ContratoRepository;
import com.inspecao.inspecao_dormentes.repository.DormenteRepository;
import com.inspecao.inspecao_dormentes.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DormenteService {

    @Autowired
    private DormenteRepository dormenteRepository;

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    public List<Dormente> listarTodos() {
        return dormenteRepository.findAll();
    }

    public Dormente buscarPorId(Long id) {
        return dormenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dormente não encontrado com o ID: " + id));
    }

    public Dormente buscarPorQrCode(String qrCode) {
        return dormenteRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Dormente não encontrado com o QR Code: " + qrCode));
    }

    public Dormente salvar(DormenteDTO dto) {
        Lote lote = loteRepository.findById(dto.getLoteId())
                .orElseThrow(() -> new RuntimeException("Lote não encontrado com o ID: " + dto.getLoteId()));

        Contrato contrato = contratoRepository.findById(dto.getContratoId())
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado com o ID: " + dto.getContratoId()));

        Dormente dormente = new Dormente();
        dormente.setNumeroSerie(dto.getNumeroSerie());
        dormente.setQrCode(dto.getQrCode());
        dormente.setTipoMadeira(dto.getTipoMadeira());
        dormente.setClasseMadeira(dto.getClasseMadeira());
        dormente.setComprimento(dto.getComprimento());
        dormente.setStatus(dto.getStatus());
        dormente.setCriadoEm(LocalDateTime.now());
        dormente.setLote(lote);
        dormente.setContrato(contrato);

        return dormenteRepository.save(dormente);
    }
}