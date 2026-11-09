package com.inspecao.inspecao_dormentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

public record RelatorioRequestDTO(
        @NotNull(message = "O ID do usuário é obrigatório")
        Integer usuarioId,

        @NotBlank(message = "O tipo do relatório é obrigatório")
        @Pattern(regexp = "ACOMPANHAMENTO|INVENTARIO", message = "O tipo deve ser ACOMPANHAMENTO ou INVENTARIO")
        String tipo,

        @NotBlank(message = "O formato é obrigatório")
        @Pattern(regexp = "PDF|XLSX|CSV", message = "O formato deve ser PDF, XLSX ou CSV")
        String formato,

        LocalDate dataInicio,

        LocalDate dataFim,

        String material,

        Integer localizacaoId,

        String graficosSelecionados,

        List<Integer> inspecoesIds
) {}