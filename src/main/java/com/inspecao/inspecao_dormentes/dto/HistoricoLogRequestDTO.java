package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HistoricoLogRequestDTO(
        @NotBlank(message = "O código da atividade é obrigatório")
        @Size(max = 50)
        String codigoAtividade,

        @NotNull(message = "O ID do usuário é obrigatório")
        Integer usuarioId,

        @NotBlank(message = "A ação é obrigatória")
        @Size(max = 50)
        String acao,

        @NotBlank(message = "A entidade é obrigatória")
        @Size(max = 50)
        String entidade,

        @NotNull(message = "O ID da entidade é obrigatório")
        Integer entidadeId,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        String dadosAnteriores,

        String dadosNovos
) {}