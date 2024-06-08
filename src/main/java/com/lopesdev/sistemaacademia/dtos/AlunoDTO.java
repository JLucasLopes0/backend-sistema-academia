package com.lopesdev.sistemaacademia.dtos;

import com.lopesdev.sistemaacademia.entities.Aluno;

import java.time.LocalDate;

public class AlunoDTO {

    private String nome;
    private int idade;
    private LocalDate dataInscricao;
    private String endereco;

    public AlunoDTO(Aluno aluno) {
        this.nome = aluno.getNome();
        this.idade = aluno.getIdade();
        this.dataInscricao = aluno.getDataInscricao();
    }

    public AlunoDTO(Aluno aluno, String endereco) {
        this.nome = aluno.getNome();
        this.idade = aluno.getIdade();
        this.dataInscricao = aluno.getDataInscricao();
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}