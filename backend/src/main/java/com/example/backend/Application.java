package com.example.backend;

import com.example.backend.controller.AtendenteController;
import com.example.backend.controller.GerenteController;
import com.example.backend.controller.ProdutoController;
import com.example.backend.model.Atendente;
import com.example.backend.model.Gerente;
import com.example.backend.model.Produto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application  {
	public static void main(String[] args) throws JsonProcessingException {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        ProdutoController produtoController = ctx.getBean(ProdutoController.class);
        Produto produto = new Produto();
        produto.setNome("Produto 1");
        produto.setDescricao("Produto 1");
        produto.setPreco(1.0);
        produtoController.inserir(produto);

        GerenteController gerenteController = ctx.getBean(GerenteController.class);
        Gerente gerente = new Gerente();
        gerente.setNome("Gerente 1");
        gerente.setLogin("Gerente 1");
        gerente.setSenha("Gerente 1");
        gerente.setCpf("Gerente 1");
		gerenteController.createGerente(gerente);


        AtendenteController atendenteController = ctx.getBean(AtendenteController.class);
        Atendente atendente = new Atendente();
        atendente.setNome("Atendente 1");
        atendente.setLogin("Atendente 1");
        atendente.setSenha("Atendente 1");
        atendente.setCpf("dbfuef");
        atendente.setGerente(gerente);
		atendenteController.salvar(atendente, 1L);


        System.out.println("It's online");

    }


}
