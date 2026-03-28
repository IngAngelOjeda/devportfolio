package com.devportfolio.controller;

import com.devportfolio.dto.ProyectoDTO;
import com.devportfolio.service.ProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoService service;

    public ProyectoController(ProyectoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProyectoDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}