package com.devportfolio.service;

import com.devportfolio.dto.ContactoRequestDTO;
import com.devportfolio.model.Contacto;
import com.devportfolio.repository.ContactoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContactoService {

    private static final Logger log = LoggerFactory.getLogger(ContactoService.class);
    private static final String DESTINO = "stacklabcontacto@gmail.com";

    private final ContactoRepository repository;
    private final JavaMailSender mailSender;

    public ContactoService(ContactoRepository repository, JavaMailSender mailSender) {
        this.repository = repository;
        this.mailSender = mailSender;
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
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setFrom("stacklabcontacto@gmail.com");
            mensaje.setTo(DESTINO);
            mensaje.setSubject("Nuevo contacto desde el portfolio — " + dto.getNombre());
            mensaje.setText(
                "Nombre: " + dto.getNombre() + "\n" +
                "Email: " + dto.getEmail() + "\n\n" +
                "Mensaje:\n" + dto.getMensaje()
            );
            mailSender.send(mensaje);
        } catch (MailException e) {
            log.error("No se pudo enviar el email de notificación para el contacto de {}: {}", dto.getEmail(), e.getMessage());
        }
    }
}