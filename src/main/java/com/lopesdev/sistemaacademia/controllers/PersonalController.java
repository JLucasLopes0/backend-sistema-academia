package com.lopesdev.sistemaacademia.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lopesdev.sistemaacademia.entities.Personal;
import com.lopesdev.sistemaacademia.services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/personal")
public class PersonalController {

    private static final Logger logger = LoggerFactory.getLogger(PersonalController.class);

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

    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    public ResponseEntity<String> cadastrarPersonal(
            @RequestPart("personal") String personalJson,
            @RequestPart("arquivo-cip") MultipartFile cip) {
        if (cip.isEmpty() || !Objects.equals(cip.getContentType(), "application/pdf")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, selecione um arquivo PDF para upload.");
        }
        try {
            // Converte o JSON do personal para objeto Personal
            ObjectMapper objectMapper = new ObjectMapper();
            Personal personal = objectMapper.readValue(personalJson, Personal.class);

            // Salva o personal e o arquivo PDF no banco de dados
            personalService.cadastrarPersonal(personal, cip);
            return ResponseEntity.status(HttpStatus.CREATED).body("Personal criado com sucesso.");
        } catch (IOException e) {
            logger.error("Erro ao realizar o upload do arquivo.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar o upload do arquivo.");
        }
    }
}