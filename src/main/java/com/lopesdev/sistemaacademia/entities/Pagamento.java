package com.lopesdev.sistemaacademia.entities;

import com.lopesdev.sistemaacademia.enums.EnumStatusPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

    @Id
    private Long idPagamento;

    private BigDecimal valorDoPagamento;

    @Enumerated(EnumType.STRING)
    private EnumStatusPagamento statusDoPagamento;

    private LocalDate dataPagamento;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Pagamento() {
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
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

}