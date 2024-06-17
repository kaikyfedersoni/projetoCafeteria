package com.example.backend.repository;

import com.example.backend.model.Pedido;
import com.example.backend.model.Produto_Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoPedidoRepository extends JpaRepository<Produto_Pedido, Long> {

    List<Produto_Pedido> findAllByPedido(Pedido pedido);

}
