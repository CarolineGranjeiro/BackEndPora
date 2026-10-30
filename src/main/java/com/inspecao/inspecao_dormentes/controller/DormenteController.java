package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.DormenteDTO;
import com.inspecao.inspecao_dormentes.model.Dormente;
import com.inspecao.inspecao_dormentes.service.DormenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dormentes")
@CrossOrigin(origins = "*")
public class DormenteController {

    @Autowired
    private DormenteService service;

    @GetMapping
    public ResponseEntity<List<Dormente>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dormente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/qrcode/{qrCode}")
    public ResponseEntity<Dormente> buscarPorQrCode(@PathVariable String qrCode) {
        return ResponseEntity.ok(service.buscarPorQrCode(qrCode));
    }

    @PostMapping
    public ResponseEntity<Dormente> cadastrar(@RequestBody DormenteDTO dto) {
        Dormente novo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }
}