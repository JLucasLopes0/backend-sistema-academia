package com.lopesdev.sistemaacademia.controllers;

import com.lopesdev.sistemaacademia.dtos.AlunoDTO;
import com.lopesdev.sistemaacademia.entities.Aluno;
import com.lopesdev.sistemaacademia.services.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    //Retorna todos os alunos do sistema (banco de dados).
    @GetMapping
    public List<Aluno> findAll(){
        return alunoService.findAll();
    }

    //Atualiza o email de um aluno existente no banco de dados ou
    //retorna uma resposta HTTP Not Found.
    @PatchMapping(value = "/{id}/atualizar-email")
    public ResponseEntity<String> atualizarEmailAluno (
            @PathVariable Long id,
            @RequestParam(name = "novoEmail") String novoEmail) {
        return alunoService.atualizarEmail(id, novoEmail);
    }

    //Retorna uma List<Aluno> com todos os alunos c/ o nome
    //fornecido ou com todos os alunos do banco.
    @GetMapping(value="/buscar")
    public List<Aluno> buscarAlunosPorNome(
            @RequestParam(name = "nome", required = false) String nome) {
        return alunoService.buscarAlunosPorNome(nome);
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

    /*
    findById para administradores do sistema, retorna Aluno
    em vez de AlunoDTO.
    */
    @GetMapping(value="/admin/{id}")
    public Aluno findByIdAdmin(@PathVariable Long id) {
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
    public String delete(@PathVariable Long id) {
        return alunoService.deleteById(id);
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