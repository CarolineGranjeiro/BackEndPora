package com.inspecao.inspecao_dormentes.controller;

import com.inspecao.inspecao_dormentes.dto.UsuarioRequestDTO;
import com.inspecao.inspecao_dormentes.dto.UsuarioResponseDTO;
import com.inspecao.inspecao_dormentes.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// ==================== NOVOS IMPORTS ====================
import com.inspecao.inspecao_dormentes.dto.SolicitarRecuperacaoDTO;
import com.inspecao.inspecao_dormentes.dto.RedefinirSenhaDTO;
import jakarta.validation.Valid;
import java.util.Map;
// =======================================================
import com.inspecao.inspecao_dormentes.dto.LoginRequestDTO;
import com.inspecao.inspecao_dormentes.dto.LoginResponseDTO;

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
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody @jakarta.validation.Valid com.inspecao.inspecao_dormentes.dto.UsuarioAtualizacaoDTO dto
    ) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    // ==================== ROTAS DE RECUPERAÇÃO DE SENHA ====================

    // Rota POST: recebe o e-mail, gera o token de 15 min e salva no banco
    @PostMapping("/esqueci-senha")
    public ResponseEntity<Map<String, String>> solicitarRecuperacao(
            @Valid @RequestBody SolicitarRecuperacaoDTO dto
    ) {
        String token = usuarioService.solicitarRecuperacao(dto.email());

        // Devolvemos o token no JSON apenas para facilitar os testes locais
        return ResponseEntity.ok(Map.of(
                "mensagem", "Instruções enviadas com sucesso.",
                "token", token
        ));
    }

    // Rota POST: valida o token, checa se expirou e grava a nova senha criptografada
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
    public ResponseEntity<LoginResponseDTO> login(@jakarta.validation.Valid @RequestBody LoginRequestDTO dto) {
        LoginResponseDTO resposta = usuarioService.autenticar(dto);
        return ResponseEntity.ok(resposta);
    }
}

