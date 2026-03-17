package com.corredor.empleo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.corredor.empleo.model.Usuario;
import com.corredor.empleo.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/registro")
    public Map<String,Object> registrar(@RequestBody Usuario u){

        usuarioRepository.save(u);

        Map<String,Object> res = new HashMap<>();
        res.put("success", true);
        res.put("message", "Usuario registrado");

        return res;
    }

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Usuario login){

        Optional<Usuario> user = usuarioRepository.findByCorreo(login.getCorreo());

        Map<String,Object> response = new HashMap<>();

        if(user.isPresent() && user.get().getPassword().equals(login.getPassword())){

            response.put("success", true);
            response.put("user", user.get());

        }else{

            response.put("success", false);
            response.put("message", "Credenciales incorrectas");

        }

        return response;
    }
}