package com.example.backend;

import com.example.backend.controller.*;
import com.example.backend.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application  {
	public static void main(String[] args) throws JsonProcessingException {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        ProdutoController produtoController = ctx.getBean(ProdutoController.class);
        Produto produto = new Produto();
        produto.setNome("Produto 1");
        produto.setDescricao("Descricao Produto 1");
        produto.setPreco(1.0);
        produtoController.salvarProduto(produto);

        Produto produto2 = new Produto();
        produto2.setNome("Produto 2");
        produto2.setDescricao("Descricao Produto 2");
        produto2.setPreco(2.0);
        produtoController.salvarProduto(produto2);

        PedidoController pedidoController = ctx.getBean(PedidoController.class);
        Date data = new Date();
        Pedido pedido = new Pedido((long) 1L, "José", data, 56.89, false);
        pedidoController.salvarPedido(pedido);
        pedido = new Pedido((long) 2L, "Carlos", data, 59, true);
        pedidoController.salvarPedido(pedido);
        pedido = new Pedido((long) 3L, "Carla", data, 556.81, false);
        pedidoController.salvarPedido(pedido);



       /*ProdutoPedidoController produtoPedidoController = ctx.getBean(ProdutoPedidoController.class);
       ProdutoPedido produtoPedido = new ProdutoPedido();
       //produtoPedido.setProduto(produto);
       produtoPedido.setQuantidade(1);
       produtoPedidoController.salvar(produtoPedido);

       ProdutoPedido produtoPedido2 = new ProdutoPedido();
       //produtoPedido2.setProduto(produto2);
       produtoPedido2.setQuantidade(2);
       produtoPedidoController.salvar(produtoPedido2);
       List<ProdutoPedido> produtosPedidos = new ArrayList<>();
       produtosPedidos.add(produtoPedido);
       produtosPedidos.add(produtoPedido2);

       PedidoController pedidoController = ctx.getBean(PedidoController.class);
       Pedido pedido = new Pedido();
       pedido.setDestinatario("Destinatario 1");
       pedido.setProdutosPedidos(produtosPedidos);
       pedido.setStatus(true);
       pedido.setAtendente(atendente);
       pedidoController.salvarPedido(pedido);
       */

        System.out.println("It's online");

    }


}
