package com.example.backend.dto;

import com.example.backend.model.Produto;

public record ProdutoRequest (String nome , String descricao , double preco) {
    public Produto toProduto(){
        return new Produto(this.nome(), this.descricao(), this.preco());
    }
}
