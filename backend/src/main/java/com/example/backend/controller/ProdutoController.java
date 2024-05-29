package com.example.backend.controller;

import com.example.backend.model.Produto;
import com.example.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService)
    {
    this.produtoService = produtoService;
    }
public ProdutoController(){}

    @GetMapping
    public List<Produto> getAll(){


        return produtoService.listar();
    }


}
