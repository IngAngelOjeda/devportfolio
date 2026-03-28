package com.devportfolio.controller;

import com.devportfolio.dto.SobreMiDTO;
import com.devportfolio.service.SobreMiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sobre-mi")
public class SobreMiController {

    private final SobreMiService service;

    public SobreMiController(SobreMiService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<SobreMiDTO> obtener() {
        SobreMiDTO dto = service.obtener();
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
}