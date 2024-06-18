package com.example.prjCafeteria.repository;

import com.example.prjCafeteria.model.Pedido;
import com.example.prjCafeteria.model.ProdutoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Long> {

    List<ProdutoPedido> findAllByPedido(Pedido pedido);

}
