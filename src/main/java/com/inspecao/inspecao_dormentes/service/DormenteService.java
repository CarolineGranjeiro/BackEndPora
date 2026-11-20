package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.DormenteRequestDTO;
import com.inspecao.inspecao_dormentes.dto.DormenteResponseDTO;
import com.inspecao.inspecao_dormentes.model.Contrato;
import com.inspecao.inspecao_dormentes.model.Dormente;
import com.inspecao.inspecao_dormentes.model.Localizacao;
import com.inspecao.inspecao_dormentes.model.Lote;
import com.inspecao.inspecao_dormentes.repository.ContratoRepository;
import com.inspecao.inspecao_dormentes.repository.DormenteRepository;
import com.inspecao.inspecao_dormentes.repository.LocalizacaoRepository;
import com.inspecao.inspecao_dormentes.repository.LoteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DormenteService {

    private final DormenteRepository dormenteRepository;
    private final LoteRepository loteRepository;
    private final ContratoRepository contratoRepository;
    private final LocalizacaoRepository localizacaoRepository;

    public DormenteService(
            DormenteRepository dormenteRepository,
            LoteRepository loteRepository,
            ContratoRepository contratoRepository,
            LocalizacaoRepository localizacaoRepository) {

        this.dormenteRepository = dormenteRepository;
        this.loteRepository = loteRepository;
        this.contratoRepository = contratoRepository;
        this.localizacaoRepository = localizacaoRepository;
    }

    @Transactional
    public DormenteResponseDTO criar(DormenteRequestDTO dto) {
        String numeroSerie = dto.numeroSerie().trim();

        // Se o QR Code não foi informado, geramos um automaticamente
        String qrCode = normalizarOpcional(dto.qrCode());
        if (qrCode == null) {
            qrCode = "QR-" + numeroSerie + "-" + java.util.UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        }

        if (dormenteRepository.findByNumeroSerie(numeroSerie).isPresent()) {
            throw new IllegalArgumentException(
                    "Já existe um cadastro com esse número de série."
            );
        }

        if (dormenteRepository.findByQrCode(qrCode).isPresent()) {
            throw new IllegalArgumentException(
                    "Já existe um cadastro com esse QR Code."
            );
        }

        Lote lote = loteRepository.findById(dto.loteId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Lote não encontrado com o ID: " + dto.loteId()
                ));

        Contrato contrato = contratoRepository.findById(dto.contratoId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Contrato não encontrado com o ID: " + dto.contratoId()
                ));

        Localizacao localizacao = localizacaoRepository
                .findById(dto.localizacaoId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Localização não encontrada com o ID: " + dto.localizacaoId()
                ));

        if (!lote.getFornecedor().getId()
                .equals(contrato.getFornecedor().getId())) {

            throw new IllegalArgumentException(
                    "O fornecedor do lote deve ser o mesmo fornecedor do contrato."
            );// muda aqui se o fornecedor for diferente

        }

        validarDimensao(dto.comprimento(), "comprimento");
        validarDimensao(dto.largura(), "largura");
        validarDimensao(dto.altura(), "altura");

        Dormente dormente = new Dormente(
                numeroSerie,
                qrCode,
                dto.tipoCadastro(),
                lote,
                contrato,
                localizacao,
                dto.material().trim(),
                dto.descricao().trim(),
                normalizarOpcional(dto.tipoMadeira()),
                normalizarOpcional(dto.classeMadeira()),
                normalizarOpcional(dto.especie()),
                dto.comprimento(),
                dto.largura(),
                dto.altura(),
                normalizarOpcional(dto.status())
        );

        Dormente salvo = dormenteRepository.save(dormente);

        return new DormenteResponseDTO(salvo);
    }
    @Transactional(readOnly = true)
    public List<DormenteResponseDTO> listarTodos() {
        return dormenteRepository
                .findAll(Sort.by("numeroSerie"))
                .stream()
                .map(DormenteResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public DormenteResponseDTO buscarPorId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID de dormente válido."
            );
        }

        Dormente dormente = dormenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Dormente não encontrado com o ID: " + id
                ));

        return new DormenteResponseDTO(dormente);
    }


    @Transactional(readOnly = true)
    public DormenteResponseDTO buscarPorQrCode(String qrCode) {
        String codigo = normalizarOpcional(qrCode);

        if (codigo == null) {
            throw new IllegalArgumentException(
                    "Informe um QR Code."
            );
        }

        Dormente dormente = dormenteRepository.findByQrCode(codigo)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Dormente não encontrado para o QR Code informado."
                ));

        return new DormenteResponseDTO(dormente);
    }

    private String normalizarOpcional(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }

        return valor.trim();
    }

    private void validarDimensao(Double valor, String campo) {
        if (valor == null || !Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException(
                    "O campo " + campo + " deve ser um número finito maior que zero."
            );
        }
    }
}