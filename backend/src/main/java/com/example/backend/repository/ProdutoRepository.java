package com.example.backend.repository;

import com.example.backend.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

    List<Produto> findAllByNomeContainingIgnoreCaseOrderByNome(String nome);

}
