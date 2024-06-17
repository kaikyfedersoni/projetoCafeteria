package com.example.prjCafeteria.repository;

import com.example.prjCafeteria.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

    List<Produto> findAllByNomeContainingIgnoreCaseOrderByNome(String nome);

}
