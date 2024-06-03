package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ProdutoPedido {
    @Id @GeneratedValue
    private Long idProduto;
    @OneToOne
    private Produto produto;

    private int quantidade;

    public Long getIdProduto() {
        return idProduto;
    }

    public ProdutoPedido(){}

    public ProdutoPedido( Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
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
