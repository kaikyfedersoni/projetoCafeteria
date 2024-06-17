package com.example.backend.repository;

import com.example.backend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByPagoFalse();

    Optional<Pedido> getAllByDataPedidoBetweenAndPagoTrue(LocalDate start, LocalDate end);

}
