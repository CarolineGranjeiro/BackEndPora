package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.ContratoRequestDTO;
import com.inspecao.inspecao_dormentes.model.Contrato;
import com.inspecao.inspecao_dormentes.service.ContratoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    private final ContratoService contratoService;

    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @PostMapping
    public ResponseEntity<Contrato> criar(@RequestBody @Valid ContratoRequestDTO dto) {
        Contrato novoContrato = contratoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoContrato);
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> listar() {
        return ResponseEntity.ok(contratoService.listarTodos());
    }
}