package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.LocalizacaoRequestDTO;
import com.inspecao.inspecao_dormentes.dto.LocalizacaoResponseDTO;
import com.inspecao.inspecao_dormentes.service.LocalizacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService localizacaoService;

    public LocalizacaoController(
            LocalizacaoService localizacaoService) {

        this.localizacaoService = localizacaoService;
    }

    @PostMapping
    public ResponseEntity<LocalizacaoResponseDTO> cadastrar(
            @RequestBody @Valid LocalizacaoRequestDTO dto) {

        LocalizacaoResponseDTO resposta =
                localizacaoService.cadastrar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<LocalizacaoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(
                localizacaoService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizacaoResponseDTO> buscarPorId(
            @PathVariable("id") Integer id) {

        return ResponseEntity.ok(
                localizacaoService.buscarPorId(id)
        );
    }
}
