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

        ProdutoPedido produtoPedido = new ProdutoPedido();
        ProdutoPedidoController produtoPedidoController = ctx.getBean(ProdutoPedidoController.class);

        pedidoController.calcularTotal(pedido.getId());

        FuncionarioController funcionarioController = ctx.getBean(FuncionarioController.class);
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Jose");
        funcionario.setCpf("123456789");
        funcionario.setLogin("jose@gmail.com");
        funcionario.setSenha("12345667");
        funcionarioController.inserir(funcionario);



        pedido = new Pedido((long) 3L, "Carla", data, 556.81, false);
        pedidoController.salvarPedido(pedido);
        produtoPedido = new ProdutoPedido(produto,pedido,15);
        produtoPedidoController.salvarProdutoPedido(new ProdutoPedidoRequestBody(1L, 3L, 6));
        produtoPedidoController.salvarProdutoPedido(new ProdutoPedidoRequestBody(2L, 3L, 8));

        pedidoController.calcularTotal(pedido.getId());


        System.out.println("It's online");

    }


}
