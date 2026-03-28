package com.devportfolio.dto;

import java.util.List;

public class SobreMiDTO {

    private String nombre;
    private String titulo;
    private String descripcion;
    private List<String> tecnologias;
    private String fotoUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String email;

    public SobreMiDTO() {}

    public SobreMiDTO(String nombre, String titulo, String descripcion,
                      List<String> tecnologias, String fotoUrl,
                      String githubUrl, String linkedinUrl, String email) {
        this.nombre = nombre;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tecnologias = tecnologias;
        this.fotoUrl = fotoUrl;
        this.githubUrl = githubUrl;
        this.linkedinUrl = linkedinUrl;
        this.email = email;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<String> getTecnologias() { return tecnologias; }
    public void setTecnologias(List<String> tecnologias) { this.tecnologias = tecnologias; }

    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }

    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }

    public String getLinkedinUrl() { return linkedinUrl; }
    public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}