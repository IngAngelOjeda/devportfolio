package com.devportfolio.service;

import com.devportfolio.dto.ExperienciaDTO;
import com.devportfolio.model.Experiencia;
import com.devportfolio.repository.ExperienciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienciaService {

    private final ExperienciaRepository repository;

    public ExperienciaService(ExperienciaRepository repository) {
        this.repository = repository;
    }

    public List<ExperienciaDTO> listar() {
        return repository.findAllByOrderByOrdenAsc().stream()
                .map(e -> new ExperienciaDTO(
                        e.getId(),
                        e.getEmpresa(),
                        e.getCargo(),
                        e.getFechaInicio(),
                        e.getFechaFin(),
                        e.getDescripcion(),
                        e.getTecnologias()
                ))
                .collect(Collectors.toList());
    }
}