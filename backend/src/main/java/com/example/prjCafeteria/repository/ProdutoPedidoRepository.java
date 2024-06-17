package com.example.prjCafeteria.repository;

import com.example.prjCafeteria.model.Pedido;
import com.example.prjCafeteria.model.Produto_Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoPedidoRepository extends JpaRepository<Produto_Pedido, Long> {

    List<Produto_Pedido> findAllByPedido(Pedido pedido);

}
