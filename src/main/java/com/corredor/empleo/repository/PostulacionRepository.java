package com.corredor.empleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corredor.empleo.model.Postulacion;

@Repository
public interface PostulacionRepository extends JpaRepository<Postulacion, Integer> {

}