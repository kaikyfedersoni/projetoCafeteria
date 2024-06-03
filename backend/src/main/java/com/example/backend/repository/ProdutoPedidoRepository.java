package com.example.backend.repository;

import com.example.backend.model.ProdutoPedido;
import com.example.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Long> {
    List<ProdutoPedido> findByProduto(Produto produto);
}
