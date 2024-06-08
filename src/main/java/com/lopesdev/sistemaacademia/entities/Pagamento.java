package com.lopesdev.sistemaacademia.entities;

import com.lopesdev.sistemaacademia.enums.EnumStatusPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

    @Id
    private long idPagamento;

    private BigDecimal valorDoPagamento;

    @Enumerated(EnumType.STRING)
    private EnumStatusPagamento statusDoPagamento;

    private int mes;
    private int ano;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public BigDecimal getValorDoPagamento() {
        return valorDoPagamento;
    }

    public void setValorDoPagamento(BigDecimal valorDoPagamento) {
        this.valorDoPagamento = valorDoPagamento;
    }

    public EnumStatusPagamento getStatusDoPagamento() {
        return statusDoPagamento;
    }

    public void setStatusDoPagamento(EnumStatusPagamento statusDoPagamento) {
        this.statusDoPagamento = statusDoPagamento;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Pagamento() {
    }

}