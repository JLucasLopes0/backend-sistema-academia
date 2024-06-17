package com.lopesdev.sistemaacademia.services;

import com.lopesdev.sistemaacademia.dtos.AlunoDTO;
import com.lopesdev.sistemaacademia.entities.Aluno;
import com.lopesdev.sistemaacademia.repositories.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void save(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    public String deleteById(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Não foi encontrado nenhum aluno com o ID fornecido. Favor verificar o ID.");
        }
        alunoRepository.deleteById(id);
        return "Aluno excluído com sucesso.";
    }

    public ResponseEntity<String> atualizarEmail(Long id, String novoEmail) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            aluno.setEmail(novoEmail);
            alunoRepository.save(aluno);
            return ResponseEntity.status(HttpStatus.OK).body("Email atualizado com sucesso para: " + novoEmail);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado com o ID: " + id);
        }
    }

    public List<Aluno> encontrarPorIdadeMaiorQue(int idade) {
        return alunoRepository.encontrarPorIdadeMaiorQue(idade);
    }

    public List<Aluno> encontrarAlunosPeloNomeDoPersonal(String nomePersonal) {
        return alunoRepository.encontrarAlunosPeloNomeDoPersonal(nomePersonal);
    }

    public List<Aluno> buscarAlunosPorNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            return findAll();
        }
        List<Aluno> todosAlunos = findAll();
        return todosAlunos.stream()
                .filter(aluno -> aluno.getNome().contains(nome))
                .collect(Collectors.toList());
    }

}