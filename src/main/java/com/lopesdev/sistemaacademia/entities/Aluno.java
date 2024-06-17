package com.lopesdev.sistemaacademia.entities;

import com.lopesdev.sistemaacademia.enums.EnumTipoTreino;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    private Integer idade;

    private LocalDate dataInscricao;

    private String endereco;

    @Enumerated(EnumType.STRING)
    private EnumTipoTreino tipoTreino;

    @ManyToOne
    @JoinColumn(name = "personal_id")
    private Personal personal;

    @OneToMany(mappedBy = "aluno")
    private List<Pagamento> pagamentos;

    public Aluno() {
    }

    public Aluno(Long id, String nome, String email, Integer idade, LocalDate dataInscricao, String endereco, EnumTipoTreino tipoTreino, Personal personal) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.dataInscricao = dataInscricao;
        this.endereco = endereco;
        this.tipoTreino = tipoTreino;
        this.personal = personal;
    }

    public Aluno(String nome) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
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

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
    
}