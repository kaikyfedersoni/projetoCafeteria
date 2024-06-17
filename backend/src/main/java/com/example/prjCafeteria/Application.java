package com.example.prjCafeteria;

import com.example.prjCafeteria.controller.*;
import com.example.prjCafeteria.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

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
        LocalDate data = LocalDate.now();
        Pedido pedido = new Pedido((long) 1L, "José", data, 56.89, false);
        pedidoController.salvarPedido(pedido);





        pedido = new Pedido((long) 2L, "Carlos", data, 59, true);
        pedidoController.salvarPedido(pedido);


        Produto_Pedido produtoPedido = new Produto_Pedido();
        ProdutoPedidoController produtoPedidoController = ctx.getBean(ProdutoPedidoController.class);
        produtoPedido = new Produto_Pedido((long)1L, "Produto 01", "Descricao 02", 26.68, 2, pedido);
        produtoPedido.setValorTotal();
        produtoPedidoController.salvarProdutoPedido(new ProdutoPedidoRequestBody(1L, 2L, 2));
        produtoPedido = new Produto_Pedido((long)2L,"Produto 02", "Descricao 03", 26.68, 6, pedido);
        produtoPedido.setValorTotal();
        produtoPedidoController.salvarProdutoPedido(new ProdutoPedidoRequestBody(2L, 2L, 6));
        produtoPedido = new Produto_Pedido((long)3L,"Produto 05", "Descricao 05", 26.68, 8, pedido);
        produtoPedido.setValorTotal();
        produtoPedidoController.salvarProdutoPedido(new ProdutoPedidoRequestBody(1L, 2L, 8));

        pedidoController.calcularTotal(pedido.getId());




        pedido = new Pedido((long) 3L, "Carla", data, 556.81, false);
        pedidoController.salvarPedido(pedido);
        produtoPedido = new Produto_Pedido("Produto 02", "Descricao 03", 26.68, 6, pedido);
        produtoPedidoController.salvarProdutoPedido(new ProdutoPedidoRequestBody(1L, 3L, 6));
        produtoPedido = new Produto_Pedido("Produto 05", "Descricao 05", 26.68, 8, pedido);
        produtoPedidoController.salvarProdutoPedido(new ProdutoPedidoRequestBody(2L, 3L, 8));

        pedidoController.calcularTotal(pedido.getId());


        System.out.println("It's online");

    }


}
