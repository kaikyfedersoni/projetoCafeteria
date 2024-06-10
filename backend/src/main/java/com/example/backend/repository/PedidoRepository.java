package com.example.backend.repository;

import com.example.backend.model.Pedido;
import com.example.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByPagoFalse();

    Optional<Pedido> getAllByDataPedidoBetweenAndPagoTrue(LocalDate start, LocalDate end);

}
