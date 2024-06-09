package com.lopesdev.sistemaacademia.dtos;

import com.lopesdev.sistemaacademia.entities.Aluno;
import com.lopesdev.sistemaacademia.entities.Personal;
import com.lopesdev.sistemaacademia.enums.EnumTipoTreino;

import java.time.LocalDate;

public class AlunoDTO {

    private String nome;
    private int idade;
    private LocalDate dataInscricao;
    private String endereco;
    private EnumTipoTreino tipoTreino;
    private Personal personal;

    public AlunoDTO(Aluno aluno) {
        this.nome = aluno.getNome();
        this.idade = aluno.getIdade();
        this.dataInscricao = aluno.getDataInscricao();
        this.tipoTreino = aluno.getTipoTreino();
        this.personal = aluno.getPersonal();
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

    public EnumTipoTreino getTipoTreino() {
        return tipoTreino;
    }

    public void setTipoTreino(EnumTipoTreino tipoTreino) {
        this.tipoTreino = tipoTreino;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

}