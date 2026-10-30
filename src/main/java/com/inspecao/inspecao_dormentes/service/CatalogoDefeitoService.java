package com.inspecao.inspecao_dormentes.service;

import com.inspecao.inspecao_dormentes.dto.DefeitoRequestDTO;
import com.inspecao.inspecao_dormentes.dto.DefeitoResponseDTO;
import com.inspecao.inspecao_dormentes.model.CatalogoDefeito;
import com.inspecao.inspecao_dormentes.repository.CatalogoDefeitoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatalogoDefeitoService {

    private final CatalogoDefeitoRepository repository;

    public CatalogoDefeitoService(CatalogoDefeitoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<DefeitoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(DefeitoResponseDTO::new)
                .toList();
    }

    @Transactional
    public DefeitoResponseDTO cadastrar(DefeitoRequestDTO dto) {
        CatalogoDefeito defeito = new CatalogoDefeito(
                dto.nome(),
                dto.descricao(),
                dto.codigoDefeito(),
                dto.severidade()
        );
        CatalogoDefeito salvo = repository.save(defeito);
        return new DefeitoResponseDTO(salvo);
    }
}