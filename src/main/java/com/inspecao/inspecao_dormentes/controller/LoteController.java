package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.LoteDTO;
import com.inspecao.inspecao_dormentes.model.Lote;
import com.inspecao.inspecao_dormentes.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
@CrossOrigin(origins = "*")
public class LoteController {

    @Autowired
    private LoteService service;

    @GetMapping
    public ResponseEntity<List<Lote>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lote> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Lote> cadastrar(@RequestBody LoteDTO dto) {
        Lote novo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }
}