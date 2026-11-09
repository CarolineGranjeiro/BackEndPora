package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.HistoricoLogRequestDTO;
import com.inspecao.inspecao_dormentes.dto.HistoricoLogResponseDTO;
import com.inspecao.inspecao_dormentes.service.HistoricoLogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico-logs")
public class HistoricoLogController {

    private final HistoricoLogService service;

    public HistoricoLogController(HistoricoLogService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<HistoricoLogResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<HistoricoLogResponseDTO>> listarPorUsuario(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<HistoricoLogResponseDTO> registrar(@RequestBody @Valid HistoricoLogRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }
}