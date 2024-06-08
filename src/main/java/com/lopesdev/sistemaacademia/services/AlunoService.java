package com.lopesdev.sistemaacademia.services;

import com.lopesdev.sistemaacademia.dtos.AlunoDTO;
import com.lopesdev.sistemaacademia.entities.Aluno;
import com.lopesdev.sistemaacademia.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public AlunoDTO findById(Long id) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            return new AlunoDTO(aluno);
        } else {
            return null;
        }
    }

    public AlunoDTO findByIdAdmin(Long id) {
        Optional<Aluno> optionalDeAluno = alunoRepository.findById(id);
        if (optionalDeAluno.isPresent()) {
            Aluno aluno = optionalDeAluno.get();
            return new AlunoDTO(aluno, aluno.getEndereco());
        } else {
            return null;
        }
    }

    public Aluno save(Aluno aluno) {
        if (aluno.getEmail() == null || aluno.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Student email is required");
        }
        if (aluno.getDataInscricao() == null) {
            throw new IllegalArgumentException("Student email is required");
        }
        return alunoRepository.save(aluno);
    }

    public void deleteById(Long id) {
        alunoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return alunoRepository.existsById(id);
    }

    public void atualizarEmail(Long id, String novoEmail) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            aluno.setEmail(novoEmail);
            alunoRepository.save(aluno);
        } else {
            throw new RuntimeException("Aluno não encontrado com o ID: " + id);
        }
    }

    public List<Aluno> encontrarPorIdadeMaiorQue(int idade) {
        return alunoRepository.encontrarPorIdadeMaiorQue(idade);
    }

    public List<Aluno> encontrarAlunosPeloNomeDoPersonal(String nomePersonal) {
        return alunoRepository.encontrarAlunosPeloNomeDoPersonal(nomePersonal);
    }

}