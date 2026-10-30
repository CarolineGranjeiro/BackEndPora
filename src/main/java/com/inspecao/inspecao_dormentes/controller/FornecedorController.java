package com.inspecao.inspecao_dormentes.controller;


import com.inspecao.inspecao_dormentes.dto.FornecedorRequestDTO;
import com.inspecao.inspecao_dormentes.model.Fornecedor;
import com.inspecao.inspecao_dormentes.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    public ResponseEntity<Fornecedor> criar(@RequestBody @Valid FornecedorRequestDTO dto) {
        Fornecedor novoFornecedor = fornecedorService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFornecedor);
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listar() {
        return ResponseEntity.ok(fornecedorService.listarTodos());
    }
}
