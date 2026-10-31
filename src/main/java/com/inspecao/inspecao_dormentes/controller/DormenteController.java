package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.DormenteRequestDTO;
import com.inspecao.inspecao_dormentes.model.Dormente;
import com.inspecao.inspecao_dormentes.service.DormenteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dormentes")
public class DormenteController {

    private final DormenteService dormenteService;

    public DormenteController(DormenteService dormenteService) {
        this.dormenteService = dormenteService;
    }

    @PostMapping
    public ResponseEntity<Dormente> criar(@RequestBody @Valid DormenteRequestDTO dto) {
        Dormente novoDormente = dormenteService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDormente);
    }

    @GetMapping
    public ResponseEntity<List<Dormente>> listar() {
        return ResponseEntity.ok(dormenteService.listarTodos());
    }
    @GetMapping("/qrcode/{qrCode}")
    public ResponseEntity<Dormente> buscarPorQrCode(@PathVariable String qrCode) {
        Dormente dormente = dormenteService.buscarPorQrCode(qrCode);
        return ResponseEntity.ok(dormente);
    }
}