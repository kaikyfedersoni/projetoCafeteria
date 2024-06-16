package com.example.backend.repository;

import com.example.backend.model.Pedido;
import com.example.backend.model.Produto;
import com.example.backend.model.Produto_Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoPedidoRepository extends JpaRepository<Produto_Pedido, Long> {

    List<Produto_Pedido> findAllByPedido(Pedido pedido);

}
