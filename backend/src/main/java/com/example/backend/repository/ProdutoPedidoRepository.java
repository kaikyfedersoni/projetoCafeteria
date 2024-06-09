package com.example.backend.repository;

import com.example.backend.model.Produto;
import com.example.backend.model.Produto_Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoPedidoRepository extends JpaRepository<Produto_Pedido, Long> {
}
