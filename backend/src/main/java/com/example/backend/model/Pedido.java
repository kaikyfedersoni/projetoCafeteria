package com.example.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pedido {
    @Id @GeneratedValue
    private Long id;
    @OneToMany
    private List<ProdutoPedido> produtosPedidos;
    private String destinatario;
    private double valor;
    private boolean status;
    @OneToOne
    private Atendente atendente;

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProdutoPedido> getProdutosPedidos() {
        return produtosPedidos;
    }

    public void setProdutosPedidos(List<ProdutoPedido> produtosPedidos) {
        this.produtosPedidos = produtosPedidos;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
