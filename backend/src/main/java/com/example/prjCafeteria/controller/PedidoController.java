package com.example.prjCafeteria.controller;

import com.example.prjCafeteria.model.Pedido;
import com.example.prjCafeteria.model.ProdutoPedido;
import com.example.prjCafeteria.repository.PedidoRepository;
import com.example.prjCafeteria.repository.ProdutoPedidoRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ProdutoPedidoRepository produtoPedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository, ProdutoPedidoRepository produtoPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoPedidoRepository = produtoPedidoRepository;
    }

    @PostMapping("/")
    public Pedido salvarPedido(@RequestBody Pedido pedido) {
        pedido.setValorTotal(0);
        pedido.setDataPedido();
        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{id}")
    public Pedido atualizarPedido(@RequestBody Pedido novoPedido, @PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setValorTotal(calcularTotal(id));
                    pedido.setPago(novoPedido.isPago());
                    return pedidoRepository.save(pedido);
                })
                .orElseGet(() -> {
                    novoPedido.setId(id);
                    return pedidoRepository.save(novoPedido);
                });
    }

    @DeleteMapping("/{id}")
    public void excluirPedido(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido not found for this id :: " + id));
        pedidoRepository.delete(pedido);
    }

    @GetMapping
    public List<Pedido> listarPedido() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido getPedidoById(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido not found for this id :: " + id));
    }

    @GetMapping("pagarPedido/{id}")
    public void pagarPedido(@PathVariable Long id) {
        Pedido pedido = getPedidoById(id);
        pedido.setPago(true);
        System.out.println(pedido);
        pedidoRepository.save(pedido);
    }

    @GetMapping("/naoPagos")
    public List<Pedido> naoPagos() {
        return pedidoRepository.findByPagoFalse();
    }

    @GetMapping("/buscarPeriodo")
    public List<Pedido> buscarPedidosPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return pedidoRepository.findAllByDataPedidoBetweenAndPagoTrue(startDate, endDate);
    }

    @GetMapping("/calcularTotal/{id}")
    public double calcularTotal(@PathVariable Long id){

        double total = 0;
        Pedido pedido = getPedidoById(id);
        List<ProdutoPedido> produtoPedidos = produtoPedidoRepository.findAllByPedido(pedido);
        for (ProdutoPedido produtoPedido : produtoPedidos){
            total += produtoPedido.getValorTotal();
        }
        pedido.setValorTotal(total);
        pedidoRepository.save(pedido);
        return total;

    }




}
