package com.example.backend.model;

import jakarta.persistence.*;

@Entity
public class Produto_Pedido {

    @Id @GeneratedValue
    private Long id;

    private String nome;
    private String descricao;
    private Double preco;
    private int quantidade;

    private double valorTotal;

    @ManyToOne
    private Pedido pedido;

    public Produto_Pedido() {    }

    public Produto_Pedido(Long id, String nome, String descricao, Double preco, int quantidade, Pedido pedido) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.pedido = pedido;
    }

    public Produto_Pedido(String nome, String descricao, Double preco, int quantidade, Pedido pedido) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.pedido = pedido;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setValorTotal() {
        this.valorTotal = this.preco * this.quantidade;
    }

    public double getValorTotal() {
        setValorTotal();
        return this.valorTotal;
    }


}
