package com.inspecao.inspecao_dormentes.model;
import com.inspecao.inspecao_dormentes.enums.StatusDormente;

import java.time.LocalDate;

public class Dormente {
    private Long id;
    private String numeroSerie;
    private String qrCode;
    private Long loteId;
    private Long contratoId;
    private String tipoMadeira;
    private String classeMadeira;
    private double comprimento;
    private StatusDormente status;
    private LocalDate criadoEm;


    //construtores

