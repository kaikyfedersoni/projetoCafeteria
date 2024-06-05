package com.example.backend;

import com.example.backend.controller.*;
import com.example.backend.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application  {
	public static void main(String[] args) throws JsonProcessingException {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        ProdutoController produtoController = ctx.getBean(ProdutoController.class);
        Produto produto = new Produto();
        produto.setNome("Produto 1");
        produto.setDescricao("Produto 1");
        produto.setPreco(1.0);
        produtoController.salvarProduto(produto);

        Produto produto2 = new Produto();
        produto2.setNome("Produto 2");
        produto2.setDescricao("Produto 2");
        produto2.setPreco(2.0);
        produtoController.salvarProduto(produto2);

        GerenteController gerenteController = ctx.getBean(GerenteController.class);
        Gerente gerente = new Gerente();
        gerente.setNome("Gerente 1");
        gerente.setLogin("Gerente 1");
        gerente.setSenha("Gerente 1");
        gerente.setCpf("Gerente 1");
		gerenteController.salvarGerente(gerente);


        AtendenteController atendenteController = ctx.getBean(AtendenteController.class);
        Atendente atendente = new Atendente();
        atendente.setNome("Atendente 1");
        atendente.setLogin("Atendente 1");
        atendente.setSenha("Atendente 1");
        atendente.setCpf("dbfuef");
        atendente.setGerente(gerente);
		atendenteController.salvarAtendente(atendente, 1L);


       ProdutoPedidoController produtoPedidoController = ctx.getBean(ProdutoPedidoController.class);
       ProdutoPedido produtoPedido = new ProdutoPedido();
       produtoPedido.setProduto(produto);
       produtoPedido.setQuantidade(1);
       produtoPedidoController.salvar(produtoPedido);

       ProdutoPedido produtoPedido2 = new ProdutoPedido();
       produtoPedido2.setProduto(produto2);
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


        System.out.println("It's online");

    }


}
