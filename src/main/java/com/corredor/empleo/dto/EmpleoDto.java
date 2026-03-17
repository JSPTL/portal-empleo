package com.corredor.empleo.dto;

public class EmpleoDto {

private String titulo;
private String descripcion;
private String ubicacion;
private Integer idEmpresa;

public String getTitulo() { return titulo; }
public void setTitulo(String titulo) { this.titulo = titulo; }

public String getDescripcion() { return descripcion; }
public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

public String getUbicacion() { return ubicacion; }
public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

public Integer getIdEmpresa() { return idEmpresa; }
public void setIdEmpresa(Integer idEmpresa) { this.idEmpresa = idEmpresa; }
}