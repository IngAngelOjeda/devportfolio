package com.devportfolio.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sobre_mi")
public class SobreMi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ElementCollection
    @CollectionTable(name = "sobre_mi_tecnologias", joinColumns = @JoinColumn(name = "sobre_mi_id"))
    @Column(name = "tecnologia")
    private List<String> tecnologias;

    private String fotoUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String email;

    public SobreMi() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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