package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.InspecaoRequestDTO;
import com.inspecao.inspecao_dormentes.dto.InspecaoResponseDTO;
import com.inspecao.inspecao_dormentes.model.Usuario;
import com.inspecao.inspecao_dormentes.service.InspecaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/inspecoes")
public class InspecaoController {

    private final InspecaoService inspecaoService;

    public InspecaoController(InspecaoService inspecaoService) {
        this.inspecaoService = inspecaoService;
    }

    @PostMapping
    public ResponseEntity<InspecaoResponseDTO> cadastrar(
            @RequestBody @Valid InspecaoRequestDTO dto,
            @AuthenticationPrincipal Usuario usuario) {

        verificarAutenticacao(usuario);

        InspecaoResponseDTO resposta =
                inspecaoService.cadastrar(dto, usuario.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<InspecaoResponseDTO>> listarTodos(
            @AuthenticationPrincipal Usuario usuario) {

        verificarAutenticacao(usuario);

        return ResponseEntity.ok(inspecaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InspecaoResponseDTO> buscarPorId(
            @PathVariable("id") Integer id,
            @AuthenticationPrincipal Usuario usuario) {

        verificarAutenticacao(usuario);

        return ResponseEntity.ok(inspecaoService.buscarPorId(id));
    }

    private void verificarAutenticacao(Usuario usuario) {
        if (usuario == null || usuario.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "É necessário estar autenticado."
            );
        }
    }
}