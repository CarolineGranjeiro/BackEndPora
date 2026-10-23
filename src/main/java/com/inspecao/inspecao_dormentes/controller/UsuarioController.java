package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.UsuarioRequestDTO;
import com.inspecao.inspecao_dormentes.dto.UsuarioResponseDTO;
import com.inspecao.inspecao_dormentes.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta classe disponibiliza rotas de API REST
@RequestMapping("/usuarios") // Prefixo para aceder a estas rotas (ex: localhost:8080/usuarios)
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Injeção da camada de serviço
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Rota POST: cria um novo utilizador
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO novoUsuario = usuarioService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // Rota GET: devolve a lista de todos os utilizadores
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }
}