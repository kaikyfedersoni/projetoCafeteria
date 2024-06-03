package com.example.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pedido {
    @Id @GeneratedValue
    private Long id;
    @OneToMany
    private List<ProdutoPedido> produtosPedidos;
    @OneToOne
    private Endereco endereco;
    private double valor;
    private boolean status;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
