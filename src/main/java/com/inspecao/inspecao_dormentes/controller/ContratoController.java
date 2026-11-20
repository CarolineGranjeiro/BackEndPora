
package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.ContratoRequestDTO;
import com.inspecao.inspecao_dormentes.dto.ContratoResponseDTO;
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
    public ResponseEntity<ContratoResponseDTO> criar(
            @RequestBody @Valid ContratoRequestDTO dto) {

        ContratoResponseDTO novoContrato = contratoService.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoContrato);
    }

    @GetMapping
    public ResponseEntity<List<ContratoResponseDTO>> listar() {

        return ResponseEntity.ok(
                contratoService.listarTodos()
        );
    }
}
