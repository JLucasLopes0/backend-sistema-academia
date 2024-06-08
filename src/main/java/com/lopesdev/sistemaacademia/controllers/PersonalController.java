package com.lopesdev.sistemaacademia.controllers;

import com.lopesdev.sistemaacademia.entities.Personal;
import com.lopesdev.sistemaacademia.services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping(value="/{id}")
    public Personal findById(@PathVariable Long id) {
        return personalService.findById(id);
    }

    @GetMapping
    public List<Personal> findAll(){
        return personalService.findAll();
    }

}