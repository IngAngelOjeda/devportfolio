package com.devportfolio.service;

import com.devportfolio.dto.ProyectoDTO;
import com.devportfolio.model.Proyecto;
import com.devportfolio.repository.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoService {

    private final ProyectoRepository repository;

    public ProyectoService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public List<ProyectoDTO> listar() {
        return repository.findAllByOrderByDestacadoDescOrdenAsc().stream()
                .map(p -> new ProyectoDTO(
                        p.getId(),
                        p.getNombre(),
                        p.getDescripcion(),
                        p.getTecnologias(),
                        p.getGithubUrl(),
                        p.getDemoUrl(),
                        p.isDestacado()
                ))
                .collect(Collectors.toList());
    }
}