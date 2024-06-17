package com.example.prjCafeteria.repository;

import com.example.prjCafeteria.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
