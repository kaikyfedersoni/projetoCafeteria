package com.example.backend.controller;


import com.example.backend.model.Produto;
import com.example.backend.model.Produto_Pedido;
import com.example.backend.repository.ProdutoPedidoRepository;
import com.example.backend.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtosPedido")
public class ProdutoPedidoController {

    private final ProdutoPedidoRepository produtoPedidoRepository;

    public ProdutoPedidoController(ProdutoPedidoRepository produtoPedidoRepository) {
        this.produtoPedidoRepository = produtoPedidoRepository;
    }


    @PostMapping
    public Produto_Pedido salvarProdutoPedido(@RequestBody Produto_Pedido produtoPedido){
        return produtoPedidoRepository.save(produtoPedido);
    }

    @DeleteMapping("/{id}")
    public void excluirProdutoPedido(@PathVariable Long id) {
        produtoPedidoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Produto_Pedido atualizarProdutoPedido(@RequestBody Produto_Pedido novoProdutoPedido, @PathVariable Long id) {
        return produtoPedidoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(novoProdutoPedido.getNome());
                    produto.setDescricao(novoProdutoPedido.getDescricao());
                    produto.setPreco(novoProdutoPedido.getPreco());
                    return produtoPedidoRepository.save(produto);
                })
                .orElseGet(() -> {
                    novoProdutoPedido.setId(id);
                    return produtoPedidoRepository.save(novoProdutoPedido);
                });
    }

    @GetMapping
    public List<Produto_Pedido> listarProdutos() {
        return produtoPedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Produto_Pedido> buscarProduto(@PathVariable Long id){
        return produtoPedidoRepository.findById(id);
    }


}
