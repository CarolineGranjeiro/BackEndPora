package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.RelatorioRequestDTO;
import com.inspecao.inspecao_dormentes.dto.RelatorioResponseDTO;
import com.inspecao.inspecao_dormentes.service.RelatorioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService service;

    public RelatorioController(RelatorioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RelatorioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RelatorioResponseDTO> criar(@RequestBody @Valid RelatorioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RelatorioResponseDTO> atualizarStatus(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body
    ) {
        String status = body.get("status");
        return ResponseEntity.ok(service.atualizarStatus(id, status));
    }
}