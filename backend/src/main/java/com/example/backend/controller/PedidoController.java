package com.example.backend.controller;

import com.example.backend.model.Pedido;
import com.example.backend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    public Pedido salvar(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{id}")
    public Pedido updatePedido(@RequestBody Pedido novoPedido, @PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setProdutosPedidos(novoPedido.getProdutosPedidos());
                    pedido.setDestinatario(novoPedido.getDestinatario());
                    pedido.setStatus(novoPedido.isStatus());
                    pedido.setValor(novoPedido.getValor());
                    return pedidoRepository.save(pedido);
                })
                .orElseGet(() -> {
                    novoPedido.setId(id);
                    return pedidoRepository.save(novoPedido);
                });
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido not found for this id :: " + id));
        pedidoRepository.delete(pedido);
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido getPedidoById(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido not found for this id :: " + id));
    }
}
