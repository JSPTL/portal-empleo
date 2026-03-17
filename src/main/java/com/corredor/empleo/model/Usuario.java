package com.corredor.empleo.model;

import jakarta.persistence.*;

@Entity
public class Usuario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

private String nombre;

@Column(unique = true)
private String correo;

private String password;

private String tipo;

public Integer getId() { return id; }

public String getNombre() { return nombre; }
public void setNombre(String nombre) { this.nombre = nombre; }

public String getCorreo() { return correo; }
public void setCorreo(String correo) { this.correo = correo; }

public String getPassword() { return password; }
public void setPassword(String password) { this.password = password; }

public String getTipo() { return tipo; }
public void setTipo(String tipo) { this.tipo = tipo; }
}
