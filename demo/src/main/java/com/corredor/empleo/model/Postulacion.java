package com.corredor.empleo.model;

import jakarta.persistence.*;

@Entity
@Table(name="postulacion")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="id_usuario")
    private Integer idUsuario;

    @Column(name="id_empleo")
    private Integer idEmpleo;

    public Integer getId() {
        return id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEmpleo() {
        return idEmpleo;
    }

    public void setIdEmpleo(Integer idEmpleo) {
        this.idEmpleo = idEmpleo;
    }

}