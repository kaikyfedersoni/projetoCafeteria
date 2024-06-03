package com.example.backend.controller;

import com.example.backend.model.ProdutoPedido;
import com.example.backend.repository.ProdutoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produto-pedido")
public class ProdutoPedidoController {

    private final ProdutoPedidoRepository produtoPedidoRepository;

    @Autowired
    public ProdutoPedidoController(ProdutoPedidoRepository produtoPedidoRepository) {
        this.produtoPedidoRepository = produtoPedidoRepository;
    }

    @PostMapping
    public ProdutoPedido salvar(@RequestBody ProdutoPedido produtoPedido) {
        return produtoPedidoRepository.save(produtoPedido);
    }

    @PutMapping("/{id}")
    public ProdutoPedido updateProdutoPedido(@RequestBody ProdutoPedido novoProdutoPedido, @PathVariable Long id) {
        return produtoPedidoRepository.findById(id)
                .map(produtoPedido -> {
                    produtoPedido.setProduto(novoProdutoPedido.getProduto());
                    produtoPedido.setQuantidade(novoProdutoPedido.getQuantidade());
                    return produtoPedidoRepository.save(produtoPedido);
                })
                .orElseGet(() -> {
                    novoProdutoPedido.setIdProduto(id);
                    return produtoPedidoRepository.save(novoProdutoPedido);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteProdutoPedido(@PathVariable Long id) {
        ProdutoPedido produtoPedido = produtoPedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProdutoPedido not found for this id :: " + id));
        produtoPedidoRepository.delete(produtoPedido);
    }

    @GetMapping
    public List<ProdutoPedido> listar() {
        return produtoPedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProdutoPedido getProdutoPedidoById(@PathVariable Long id) {
        return produtoPedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProdutoPedido not found for this id :: " + id));
    }
}

