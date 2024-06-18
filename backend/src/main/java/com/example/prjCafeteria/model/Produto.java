package com.example.prjCafeteria.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Produto {
    @Id @GeneratedValue
    private Long id;

    private String nome;

    private String descricao;

    private double preco;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProdutoPedido> produtoPedido;

    public Produto() {}

    public Produto( String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
