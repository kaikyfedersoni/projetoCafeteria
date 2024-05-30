package com.example.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application  {
	public static void main(String[] args) throws JsonProcessingException {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
//		ProdutoService produtoService = ctx.getBean(ProdutoService.class);
//		ProdutoController produtoController = new ProdutoController(produtoService);
		System.out.println("It's online");

	}


}
