package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.DefeitoRequestDTO;
import com.inspecao.inspecao_dormentes.dto.DefeitoResponseDTO;
import com.inspecao.inspecao_dormentes.service.CatalogoDefeitoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/defeitos")
public class CatalogoDefeitoController {

    private final CatalogoDefeitoService service;

    public CatalogoDefeitoController(CatalogoDefeitoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DefeitoResponseDTO>> listarTodos() {
        List<DefeitoResponseDTO> defeitos = service.listarTodos();
        return ResponseEntity.ok(defeitos);
    }

    @PostMapping
    public ResponseEntity<DefeitoResponseDTO> cadastrar(@RequestBody @Valid DefeitoRequestDTO dto) {
        DefeitoResponseDTO novoDefeito = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDefeito);
    }
}