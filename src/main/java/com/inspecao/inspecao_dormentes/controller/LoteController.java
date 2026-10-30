package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.LoteRequestDTO;
import com.inspecao.inspecao_dormentes.model.Lote;
import com.inspecao.inspecao_dormentes.service.LoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
public class LoteController {

    private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @PostMapping
    public ResponseEntity<Lote> criar(@RequestBody @Valid LoteRequestDTO dto) {
        Lote novoLote = loteService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLote);
    }

    @GetMapping
    public ResponseEntity<List<Lote>> listar() {
        return ResponseEntity.ok(loteService.listarTodos());
    }
}