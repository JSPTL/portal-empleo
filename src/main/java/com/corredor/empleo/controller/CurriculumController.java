package com.corredor.empleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.corredor.empleo.model.Curriculum;
import com.corredor.empleo.repository.CurriculumRepository;

@RestController
@RequestMapping("/curriculum")
@CrossOrigin(origins = "*")
public class CurriculumController {

    @Autowired
    CurriculumRepository curriculumRepository;

    @PostMapping
    public Curriculum guardar(@RequestBody Curriculum c){
        return curriculumRepository.save(c);
    }

}