package com.lopesdev.sistemaacademia.services;

import com.lopesdev.sistemaacademia.dtos.AlunoDTO;
import com.lopesdev.sistemaacademia.entities.Aluno;
import com.lopesdev.sistemaacademia.repositories.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Aluno findByIdAdmin(Long id) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        return optionalAluno.orElse(null);
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

    public String deleteById(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Não foi encontrado nenhum aluno com o ID fornecido. Favor verificar o ID.");
        }
        alunoRepository.deleteById(id);
        return "Aluno excluído com sucesso.";
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