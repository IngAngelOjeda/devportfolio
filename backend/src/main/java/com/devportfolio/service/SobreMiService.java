package com.devportfolio.service;

import com.devportfolio.dto.SobreMiDTO;
import com.devportfolio.model.SobreMi;
import com.devportfolio.repository.SobreMiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SobreMiService {

    private final SobreMiRepository repository;

    public SobreMiService(SobreMiRepository repository) {
        this.repository = repository;
    }

    public SobreMiDTO obtener() {
        List<SobreMi> registros = repository.findAll();
        if (registros.isEmpty()) {
            return null;
        }
        SobreMi sm = registros.get(0);
        return new SobreMiDTO(
                sm.getNombre(),
                sm.getTitulo(),
                sm.getDescripcion(),
                sm.getTecnologias(),
                sm.getFotoUrl(),
                sm.getGithubUrl(),
                sm.getLinkedinUrl(),
                sm.getEmail()
        );
    }
}