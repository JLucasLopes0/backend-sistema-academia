package com.lopesdev.sistemaacademia.services;

import com.lopesdev.sistemaacademia.entities.Personal;
import com.lopesdev.sistemaacademia.repositories.PersonalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonalService {

    @Autowired
    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    public List<Personal> findAll() {
        return personalRepository.findAll();
    }

    public Personal findById(Long id) {
        Personal personal = null;
        try {
            Optional<Personal> optionalPersonal = personalRepository.findById(id);
            if (optionalPersonal.isEmpty()) {
                throw new EntityNotFoundException("Personal não encontrado com o ID " + id);
            } else {
                personal = optionalPersonal.get();
            }
        } catch (EntityNotFoundException e) {
            String message = "Personal não encontrado com o ID " + id;
            System.out.println(message);
        }
        return personal;
    }

    public void cadastrarPersonal(Personal personal, MultipartFile cip) throws IOException {
        //Salva o arquivo como bytes
        personal.setCip(cip.getBytes());
        //Salva o personal no banco de dados
        personalRepository.save(personal);
    }
}