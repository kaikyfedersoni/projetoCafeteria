package com.example.backend.repository;

import com.example.backend.model.Atendente;
import com.example.backend.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
    List<Atendente> findByGerente(Gerente gerente);
}
