package com.lopesdev.sistemaacademia.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String nome;

    @OneToMany(mappedBy = "personal")
    private List<Aluno> alunos;

    public Personal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}