package com.example.prjCafeteria.controller;


import com.example.prjCafeteria.model.Pedido;
import com.example.prjCafeteria.model.Produto;
import com.example.prjCafeteria.model.ProdutoPedidoRequestBody;
import com.example.prjCafeteria.model.ProdutoPedido;
import com.example.prjCafeteria.repository.PedidoRepository;
import com.example.prjCafeteria.repository.ProdutoPedidoRepository;
import com.example.prjCafeteria.repository.ProdutoRepository;
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

    @PostMapping("/")
    public ProdutoPedido salvarProdutoPedido(@RequestBody ProdutoPedidoRequestBody requestBody){
        Optional<Produto> produto = produtoRepository.findById(requestBody.getIdProduto());
        Optional<Pedido> pedido = pedidoRepository.findById(requestBody.getIdPedido());
        ProdutoPedido produtoPedido = new ProdutoPedido(produto.get(), pedido.get(), requestBody.getQuantidade());
        return produtoPedidoRepository.save(produtoPedido);
    }

    @DeleteMapping("/{id}")
    public void excluirProdutoPedido(@PathVariable Long id) {
        produtoPedidoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProdutoPedido atualizarProdutoPedido(@RequestBody ProdutoPedidoRequestBody requestBody, @PathVariable Long id) {
        return produtoPedidoRepository.findById(id)
                .map(produtoPedido -> {
                    produtoPedido.setQuantidade(requestBody.getQuantidade());
                    return produtoPedidoRepository.save(produtoPedido);
                })
                .orElseGet(() -> null);
    }

    @GetMapping
    public List<ProdutoPedido> listarProdutos() {
        return produtoPedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ProdutoPedido> buscarProduto(@PathVariable Long id){
        return produtoPedidoRepository.findById(id);
    }

    @GetMapping("/pedidos/{id}")
    public List<ProdutoPedido> buscarPedidosPorId(@PathVariable Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return produtoPedidoRepository.findAllByPedido(pedido.get());
    }




}
