package com.devportfolio.dto;

import java.util.List;

public class ExperienciaDTO {

    private Long id;
    private String empresa;
    private String cargo;
    private String fechaInicio;
    private String fechaFin;
    private String descripcion;
    private List<String> tecnologias;

    public ExperienciaDTO() {}

    public ExperienciaDTO(Long id, String empresa, String cargo, String fechaInicio,
                          String fechaFin, String descripcion, List<String> tecnologias) {
        this.id = id;
        this.empresa = empresa;
        this.cargo = cargo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.tecnologias = tecnologias;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<String> getTecnologias() { return tecnologias; }
    public void setTecnologias(List<String> tecnologias) { this.tecnologias = tecnologias; }
}