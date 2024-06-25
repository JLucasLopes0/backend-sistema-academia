package com.lopesdev.sistemaacademia.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;

    @OneToMany(mappedBy = "personal")
    private List<Aluno> alunos;

    @Lob
    @Column(name = "cip")
    public byte[] cip;

    public Personal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public byte[] getCip() {
        return cip;
    }

    public void setCip(byte[] cip) {
        this.cip = cip;
    }

}