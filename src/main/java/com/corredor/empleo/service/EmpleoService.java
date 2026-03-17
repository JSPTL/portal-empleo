package com.corredor.empleo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corredor.empleo.model.Empleo;
import com.corredor.empleo.repository.EmpleoRepository;

@Service
public class EmpleoService {

@Autowired
private EmpleoRepository empleoRepository;

public List<Empleo> listarEmpleos(){
return empleoRepository.findAll();
}

public Empleo crearEmpleo(Empleo e){
return empleoRepository.save(e);
}

public void eliminarEmpleo(Integer id){
empleoRepository.deleteById(id);
}

public Empleo actualizarEmpleo(Integer id, Empleo nuevo){

Empleo empleo = empleoRepository.findById(id).orElse(null);

if(empleo != null){

empleo.setTitulo(nuevo.getTitulo());
empleo.setDescripcion(nuevo.getDescripcion());
empleo.setUbicacion(nuevo.getUbicacion());

return empleoRepository.save(empleo);

}

return null;

}

}