package com.devportfolio.controller;

import com.devportfolio.dto.ContactoRequestDTO;
import com.devportfolio.service.ContactoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/contacto")
public class ContactoController {

    private final ContactoService service;

    public ContactoController(ContactoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> recibir(@Valid @RequestBody ContactoRequestDTO dto) {
        service.guardar(dto);
        return ResponseEntity.status(201).body(Map.of("mensaje", "Mensaje recibido. ¡Gracias por escribir!"));
    }
}