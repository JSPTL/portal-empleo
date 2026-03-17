package com.corredor.empleo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.corredor.empleo.model.Empleo;
import com.corredor.empleo.service.EmpleoService;

@RestController
@RequestMapping("/empleos")
@CrossOrigin(origins="*")
public class EmpleoController {

@Autowired
private EmpleoService empleoService;

@GetMapping
public List<Empleo> listar(){
return empleoService.listarEmpleos();
}

@PostMapping
public Empleo crear(@RequestBody Empleo e){
return empleoService.crearEmpleo(e);
}

@DeleteMapping("/{id}")
public void eliminar(@PathVariable Integer id){
empleoService.eliminarEmpleo(id);
}

@PutMapping("/{id}")
public Empleo actualizar(@PathVariable Integer id, @RequestBody Empleo nuevo){
return empleoService.actualizarEmpleo(id, nuevo);
}

}