package com.example.backend.controller;


import com.example.backend.model.Pedido;
import com.example.backend.model.Produto;
import com.example.backend.model.Produto_Pedido;
import com.example.backend.repository.PedidoRepository;
import com.example.backend.repository.ProdutoPedidoRepository;
import com.example.backend.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtosPedido")
public class ProdutoPedidoController {

    private final ProdutoPedidoRepository produtoPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public ProdutoPedidoController(ProdutoPedidoRepository produtoPedidoRepository, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.produtoPedidoRepository = produtoPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto_Pedido salvarProdutoPedido(@RequestBody Produto_Pedido produtoPedido, @RequestBody Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        produtoPedido.setValorTotal();
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
                    produto.setQuantidade(novoProdutoPedido.getQuantidade());
                    produto.setValorTotal();
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

    @GetMapping("/pedidos/{id}")
    public List<Produto_Pedido> buscarPedidosPorId(@PathVariable Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return produtoPedidoRepository.findAllByPedido(pedido.get());
    }





}
