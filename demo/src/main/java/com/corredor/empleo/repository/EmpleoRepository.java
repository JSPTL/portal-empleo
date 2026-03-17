package com.corredor.empleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.corredor.empleo.model.Empleo;

public interface EmpleoRepository extends JpaRepository<Empleo,Integer>{
}