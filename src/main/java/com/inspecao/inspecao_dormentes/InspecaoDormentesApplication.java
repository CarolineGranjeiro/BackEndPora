package com.inspecao.inspecao_dormentes;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class InspecaoDormentesApplication {

    public static void main(String[] args) {
        SpringApplication.run(InspecaoDormentesApplication.class, args);
    }

    @PostConstruct
    public void init() {
        // Força a aplicação a usar o fuso horário de Brasília
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }

}