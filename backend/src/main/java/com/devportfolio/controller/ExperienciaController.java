package com.devportfolio.controller;

import com.devportfolio.dto.ExperienciaDTO;
import com.devportfolio.service.ExperienciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/experiencias")
public class ExperienciaController {

    private final ExperienciaService service;

    public ExperienciaController(ExperienciaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ExperienciaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}