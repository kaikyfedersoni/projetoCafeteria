package com.example.backend.controller;


import com.example.backend.model.Produto;
import com.example.backend.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    @PostMapping
    public Produto salvarProduto(@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@RequestBody Produto novoProduto, @PathVariable Long id) {
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
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Produto> buscarProduto(@PathVariable Long id){
        return produtoRepository.findById(id);
    }

    @GetMapping("/nome/{nome}")
    public List<Produto> buscarProdutoPorNome(@PathVariable String nome){
        return produtoRepository.findAllByNomeContainingIgnoreCaseOrderByNome(nome);
    }


}
