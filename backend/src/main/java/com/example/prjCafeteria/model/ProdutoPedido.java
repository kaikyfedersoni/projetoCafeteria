package com.example.prjCafeteria.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
public class ProdutoPedido {

    @Id @GeneratedValue
    private Long id;


    @ManyToOne()
    private Produto produto = new Produto();
    private int quantidade;



    @ManyToOne()
    private Pedido pedido;


    public ProdutoPedido() {    }

    public ProdutoPedido(Produto produto, Pedido pedido, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
        this.pedido = pedido;
    }

    public ProdutoPedido(Long id, Produto produto, int quantidade, Pedido pedido) {
        this.id = id;
        this.produto=produto;
        this.quantidade = quantidade;
        this.pedido = pedido;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }



    public double getValorTotal() {
        return this.produto.getPreco() * this.quantidade;
    }


}
