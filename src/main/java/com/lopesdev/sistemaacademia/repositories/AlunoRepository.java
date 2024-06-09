package com.lopesdev.sistemaacademia.repositories;

import com.lopesdev.sistemaacademia.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository <Aluno, Long> {

    /*
    Consulta JPQL para obter todos os alunos c/
    idade superior a determinado valor.
    */
    @Query("SELECT a FROM Aluno a WHERE a.idade > :idade")
    List<Aluno> encontrarPorIdadeMaiorQue(@Param("idade") int idade);

    /*
    Consulta JPQL para obter todos os alunos de um personal
    a partir do seu nome.
    */
    @Query("SELECT a FROM Aluno a WHERE LOWER(a.personal.nome) = LOWER(:nomePersonal)")
    List<Aluno> encontrarAlunosPeloNomeDoPersonal(@Param("nomePersonal") String nomePersonal);

}