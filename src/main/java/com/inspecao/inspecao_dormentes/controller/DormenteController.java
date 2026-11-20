
package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.DormenteRequestDTO;
import com.inspecao.inspecao_dormentes.dto.DormenteResponseDTO;
import com.inspecao.inspecao_dormentes.service.DormenteService;
import com.inspecao.inspecao_dormentes.service.QrCodeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dormentes")
@CrossOrigin(origins = "*")
public class DormenteController {

    private final DormenteService dormenteService;
    private final QrCodeService qrCodeService;

    public DormenteController(
            DormenteService dormenteService,
            QrCodeService qrCodeService) {

        this.dormenteService = dormenteService;
        this.qrCodeService = qrCodeService;
    }

    @PostMapping
    public ResponseEntity<DormenteResponseDTO> criar(
            @RequestBody @Valid DormenteRequestDTO dto) {

        DormenteResponseDTO resposta = dormenteService.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<DormenteResponseDTO>> listar() {
        return ResponseEntity.ok(
                dormenteService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DormenteResponseDTO> buscarPorId(
            @PathVariable("id") Integer id) {

        return ResponseEntity.ok(
                dormenteService.buscarPorId(id)
        );
    }

    @GetMapping("/qrcode/{qrCode}")
    public ResponseEntity<DormenteResponseDTO> buscarPorQrCode(
            @PathVariable("qrCode") String qrCode) {

        return ResponseEntity.ok(
                dormenteService.buscarPorQrCode(qrCode)
        );
    }

    @GetMapping(
            value = "/{id}/qrcode/imagem",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public ResponseEntity<byte[]> gerarImagemQrCode(
            @PathVariable("id") Integer id) {

        DormenteResponseDTO dormente = dormenteService.buscarPorId(id);

        if (dormente.qrCode() == null || dormente.qrCode().isBlank()) {
            throw new IllegalStateException(
                    "Este dormente não possui um QR Code."
            );
        }

        byte[] imagem = qrCodeService.gerarImagem(dormente.qrCode());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imagem);
    }
}
