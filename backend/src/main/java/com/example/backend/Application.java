package com.example.backend;

import com.example.backend.model.Produto;
import com.example.backend.service.ProdutoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("It`s online");
		ProdutoService produtoService = new ProdutoService();
		Produto produto = new Produto();

		produto.setNome("Produto 1");
		produto.setDescricao("Produto 1");
		produto.setPreco(1.0);
		//produtoService.salvar(produto);


	}

}
