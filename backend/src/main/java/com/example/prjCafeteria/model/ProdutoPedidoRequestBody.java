package com.example.prjCafeteria.model;

public class ProdutoPedidoRequestBody {

    private Long idProduto;
    private Long idPedido;
    private int quantidade;

    public ProdutoPedidoRequestBody() {
    }

    public ProdutoPedidoRequestBody(Long idProduto, Long idPedido, int quantidade) {
        this.idProduto = idProduto;
        this.idPedido = idPedido;
        this.quantidade = quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
