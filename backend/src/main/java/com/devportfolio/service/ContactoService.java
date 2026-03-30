package com.devportfolio.service;

import com.devportfolio.dto.ContactoRequestDTO;
import com.devportfolio.model.Contacto;
import com.devportfolio.repository.ContactoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ContactoService {

    private static final Logger log = LoggerFactory.getLogger(ContactoService.class);
    private static final String DESTINO = "stacklabcontacto@gmail.com";
    private static final String RESEND_URL = "https://api.resend.com/emails";

    private final ContactoRepository repository;
    private final RestClient restClient;
    private final String apiKey;
    private final String from;

    public ContactoService(
            ContactoRepository repository,
            RestClient.Builder restClientBuilder,
            @Value("${resend.api-key}") String apiKey,
            @Value("${resend.from}") String from) {
        this.repository = repository;
        this.restClient = restClientBuilder.build();
        this.apiKey = apiKey;
        this.from = from;
    }

    public void guardar(ContactoRequestDTO dto) {
        Contacto contacto = new Contacto();
        contacto.setNombre(dto.getNombre());
        contacto.setEmail(dto.getEmail());
        contacto.setMensaje(dto.getMensaje());
        contacto.setFechaRecibido(LocalDateTime.now());
        contacto.setLeido(false);
        repository.save(contacto);

        try {
            Map<String, Object> body = Map.of(
                "from", from,
                "to", List.of(DESTINO),
                "subject", "Nuevo contacto desde el portfolio — " + dto.getNombre(),
                "text", "Nombre: " + dto.getNombre() + "\n" +
                        "Email: " + dto.getEmail() + "\n\n" +
                        "Mensaje:\n" + dto.getMensaje()
            );

            restClient.post()
                .uri(RESEND_URL)
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();

            log.info("Email enviado vía Resend para contacto de {}", dto.getEmail());
        } catch (Exception e) {
            log.error("No se pudo enviar el email vía Resend para el contacto de {}: {}", dto.getEmail(), e.getMessage());
        }
    }
}
