package com.example.backend.repository;

import com.example.backend.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository  extends JpaRepository<Produto, Long> {
}
