package com.inspecao.inspecao_dormentes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata regras de negócio, como e-mail duplicado ou ID não encontrado
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> tratarRegraDeNegocio(IllegalArgumentException ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", LocalDateTime.now());
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", "Requisição Inválida");
        resposta.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    // Trata validações dos DTOs (@NotBlank, @NotNull, @Size, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacoes(MethodArgumentNotValidException ex) {
        Map<String, String> camposComErro = new HashMap<>();
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            camposComErro.put(erro.getField(), erro.getDefaultMessage());
        }

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", LocalDateTime.now());
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", "Validação de Campos");
        resposta.put("campos", camposComErro);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}
