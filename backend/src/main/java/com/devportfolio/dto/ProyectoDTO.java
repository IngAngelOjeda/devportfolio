package com.devportfolio.dto;

import java.util.List;

public class ProyectoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private List<String> tecnologias;
    private String githubUrl;
    private String demoUrl;
    private boolean destacado;

    public ProyectoDTO() {}

    public ProyectoDTO(Long id, String nombre, String descripcion, List<String> tecnologias,
                       String githubUrl, String demoUrl, boolean destacado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tecnologias = tecnologias;
        this.githubUrl = githubUrl;
        this.demoUrl = demoUrl;
        this.destacado = destacado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<String> getTecnologias() { return tecnologias; }
    public void setTecnologias(List<String> tecnologias) { this.tecnologias = tecnologias; }

    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }

    public String getDemoUrl() { return demoUrl; }
    public void setDemoUrl(String demoUrl) { this.demoUrl = demoUrl; }

    public boolean isDestacado() { return destacado; }
    public void setDestacado(boolean destacado) { this.destacado = destacado; }
}