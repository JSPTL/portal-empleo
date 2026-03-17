package com.corredor.empleo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.corredor.empleo.model.Postulacion;
import com.corredor.empleo.service.PostulacionService;

@RestController
@RequestMapping("/postulaciones")
@CrossOrigin(origins = "*")
public class PostulacionController {

    @Autowired
    private PostulacionService postulacionService;

    // Listar postulaciones
    @GetMapping
    public List<Postulacion> listar() {
        return postulacionService.listar();
    }

    // Crear postulacion
    @PostMapping
    public Postulacion crear(@RequestBody Postulacion p) {
        return postulacionService.crear(p);
    }

    // Eliminar postulacion
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        postulacionService.eliminar(id);
    }

    // Actualizar postulacion
    @PutMapping("/{id}")
    public Postulacion actualizar(@PathVariable Integer id, @RequestBody Postulacion p) {
        return postulacionService.actualizar(id, p);
    }

}