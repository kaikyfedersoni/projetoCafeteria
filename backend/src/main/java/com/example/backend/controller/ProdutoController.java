package com.example.backend.controller;

import com.example.backend.dto.ProdutoRequest;
import com.example.backend.dto.ProdutoResponse;
import com.example.backend.model.Produto;
import com.example.backend.repository.ProdutoRepository;
import com.example.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    @PostMapping
    public Produto inserir(@RequestBody ProdutoRequest request){
        Produto produto = request.toProduto();
        return produtoRepository.save(produto);
    }




}
