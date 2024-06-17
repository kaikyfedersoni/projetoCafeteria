package com.example.prjCafeteria.repository;

import com.example.prjCafeteria.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByPagoFalse();

    List<Pedido> findAllByDataPedidoBetweenAndPagoTrue(LocalDate startDate, LocalDate endDate);
}
