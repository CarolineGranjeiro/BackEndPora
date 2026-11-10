package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.DetalheInspecaoResponseDTO;
import com.inspecao.inspecao_dormentes.model.Usuario;
import com.inspecao.inspecao_dormentes.service.DetalheInspecaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/inspecoes/{inspecaoId}/detalhes")
public class DetalheInspecaoController {

    private final DetalheInspecaoService detalheInspecaoService;

    public DetalheInspecaoController(
            DetalheInspecaoService detalheInspecaoService) {

        this.detalheInspecaoService = detalheInspecaoService;
    }

    @GetMapping
    public ResponseEntity<List<DetalheInspecaoResponseDTO>> listar(
            @PathVariable("inspecaoId") Integer inspecaoId,
            @AuthenticationPrincipal Usuario usuario) {

        if (usuario == null || usuario.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "É necessário estar autenticado."
            );
        }

        return ResponseEntity.ok(
                detalheInspecaoService.listarPorInspecao(inspecaoId)
        );
    }
}