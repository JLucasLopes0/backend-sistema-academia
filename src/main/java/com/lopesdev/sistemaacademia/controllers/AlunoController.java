package com.lopesdev.sistemaacademia.controllers;

import com.lopesdev.sistemaacademia.dtos.AlunoDTO;
import com.lopesdev.sistemaacademia.entities.Aluno;
import com.lopesdev.sistemaacademia.services.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> findAll(){
        return alunoService.findAll();
    }

    @PatchMapping(value = "/{id}/atualizar-email")
    public ResponseEntity<String> atualizarEmailAluno (
            @PathVariable Long id,
            @RequestParam(name = "novoEmail") String novoEmail) {
        //Verifica se existe um aluno com o ID fornecido
        if (!alunoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado com o ID: " + id);
        }
        //Atualiza o email do aluno com base em seu ID
        alunoService.atualizarEmail(id, novoEmail);
        return ResponseEntity.status(HttpStatus.OK).body("Email atualizado com sucesso para: " + novoEmail);
    }

    @GetMapping(value="/buscar")
    public List<Aluno> buscarAlunoPorNome(
            @RequestParam(name = "nome", required = false) String nome) {
        if (nome == null || nome.isEmpty()) {
            alunoService.findAll();
        }
        //Obtém todos os alunos
        List<Aluno> todosAlunos = alunoService.findAll();
        //Filtra os alunos com base no nome
        List<Aluno> resultado = todosAlunos.stream()
                .filter(aluno -> aluno.getNome().contains(nome))
                .collect(Collectors.toList());
        return resultado;
    }

    /*
    Retorna um AlunoDTO com base no ID do respectivo Aluno.
    O AlunoDTO retornado por esse método oculta certas propriedades
    do Aluno.
    */
    @GetMapping(value="/{id}")
    public AlunoDTO findById(@PathVariable Long id){
        return alunoService.findById(id);
    }

    @GetMapping(value="/admin/{id}")
    /*
    findById para administradores do sistema, retorna todas
    as informações do Aluno via AlunoDTO.
    */
    public AlunoDTO findByIdAdmin(@PathVariable Long id) {
        return alunoService.findByIdAdmin(id);
    }

    @PostMapping
    public Aluno insert(@RequestBody @Valid Aluno aluno) {
        return alunoService.save(aluno);
    }

    @PutMapping(value="/{id}")
    public Aluno update(@RequestBody Aluno aluno) {
        return alunoService.save(aluno);
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable Long id) {
        alunoService.deleteById(id);
    }

   @GetMapping(value="/idademaiorque")
    public List<Aluno> encontrarPorIdadeMaiorQue
            (@RequestParam(name = "idade") int idade) {
        return alunoService.encontrarPorIdadeMaiorQue(idade);
    }

    @GetMapping(value="/encontrarosalunosdopersonal")
    public List<Aluno> encontrarAlunosPeloNomeDoPersonal
            (@RequestParam(name = "nome") String nomePersonal) {
        return alunoService.encontrarAlunosPeloNomeDoPersonal(nomePersonal);
    }

}