package com.example.backend.controller;

import com.example.backend.dto.ProdutoRequest;
import com.example.backend.model.Produto;
import com.example.backend.repository.ProdutoRepository;
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
    public Produto inserir(@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@RequestBody Produto novoProduto, @PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(novoProduto.getNome());
                    produto.setDescricao(novoProduto.getDescricao());
                    produto.setPreco(novoProduto.getPreco());
                    return produtoRepository.save(produto);
                })
                .orElseGet(() -> {
                    novoProduto.setId(id);
                    return produtoRepository.save(novoProduto);
                });
    }

    @GetMapping
    public List<Produto> all() {
        return produtoRepository.findAll();
    }




}
