package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ProdutoPedido {
    @Id @GeneratedValue
    private Long id;
    @OneToOne
    private Produto produto;

    private int quantidade;

    public Long getId() {
        return id;
    }

    public ProdutoPedido(){}

    public ProdutoPedido( Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void setId(Long idProduto) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
