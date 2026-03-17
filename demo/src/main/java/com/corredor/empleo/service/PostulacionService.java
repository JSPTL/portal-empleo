package com.corredor.empleo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corredor.empleo.model.Postulacion;
import com.corredor.empleo.repository.PostulacionRepository;
import com.corredor.empleo.exception.ResourceNotFoundException;

@Service
public class PostulacionService {

    @Autowired
    private PostulacionRepository postulacionRepository;

    // Obtener todas las postulaciones
    public List<Postulacion> listar() {
        return postulacionRepository.findAll();
    }

    // Crear postulacion
    public Postulacion crear(Postulacion p) {
        return postulacionRepository.save(p);
    }

    // Eliminar postulacion
    public void eliminar(Integer id) {
        postulacionRepository.deleteById(id);
    }

    // Actualizar postulacion
    public Postulacion actualizar(Integer id, Postulacion nueva) {

        Postulacion p = postulacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postulación no encontrada"));

        p.setIdUsuario(nueva.getIdUsuario());
        p.setIdEmpleo(nueva.getIdEmpleo());

        return postulacionRepository.save(p);
    }

}