package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.UsuarioRequestDTO;
import com.inspecao.inspecao_dormentes.dto.UsuarioResponseDTO;
import com.inspecao.inspecao_dormentes.dto.SolicitarRecuperacaoDTO;
import com.inspecao.inspecao_dormentes.dto.RedefinirSenhaDTO;
import com.inspecao.inspecao_dormentes.dto.LoginRequestDTO;
import com.inspecao.inspecao_dormentes.dto.LoginResponseDTO;
import com.inspecao.inspecao_dormentes.model.Usuario;
import com.inspecao.inspecao_dormentes.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

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

    // ==================== ENDPOINT DO USUÁRIO LOGADO ====================
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> obterUsuarioLogado(
            @AuthenticationPrincipal Usuario usuarioLogado
    ) {
        return ResponseEntity.ok(new UsuarioResponseDTO(usuarioLogado));
    }

    // ==================== CONSULTA POR ID ====================
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    // ==================== ATUALIZAÇÃO ====================
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid com.inspecao.inspecao_dormentes.dto.UsuarioAtualizacaoDTO dto
    ) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    // ==================== EXCLUSÃO ====================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ==================== ROTAS DE RECUPERAÇÃO DE SENHA ====================
    @PostMapping("/esqueci-senha")
    public ResponseEntity<Map<String, String>> solicitarRecuperacao(
            @Valid @RequestBody SolicitarRecuperacaoDTO dto
    ) {
        String token = usuarioService.solicitarRecuperacao(dto.email());

        return ResponseEntity.ok(Map.of(
                "mensagem", "Instruções enviadas com sucesso.",
                "token", token
        ));
    }

    @PostMapping("/redefinir-senha")
    public ResponseEntity<Map<String, String>> redefinirSenha(
            @Valid @RequestBody RedefinirSenhaDTO dto
    ) {
        usuarioService.redefinirSenha(dto.token(), dto.novaSenha());

        return ResponseEntity.ok(Map.of(
                "mensagem", "Senha redefinida com sucesso!"
        ));
    }

    // ==================== ENDPOINT DE LOGIN ====================
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        LoginResponseDTO resposta = usuarioService.autenticar(dto);
        return ResponseEntity.ok(resposta);
    }
}