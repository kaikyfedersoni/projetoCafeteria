package com.example.backend.service;

import com.example.backend.model.Produto;
import com.example.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoService() {

    }
    @Transactional
    public Produto salvar(Produto produto) {
        boolean existe = produtoRepository.existsById(produto.getId());
        if (existe) {
            throw new RuntimeException("Produto já existe");
        }
        return produtoRepository.save(produto);
    }

    @Transactional
    public void excluir(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produtoRepository.delete(produto);
    }

    @Transactional
    public Produto atualizar(Produto produtoSelecionado, Long idProduto ) {

        Produto produtoAtual = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoAtual.setNome(produtoSelecionado.getNome());
        produtoAtual.setDescricao(produtoSelecionado.getDescricao());
        produtoAtual.setPreco(produtoSelecionado.getPreco());
        return produtoRepository.save(produtoAtual);
    }

    @Transactional(readOnly = true)
    public List<Produto> listar(){
        return produtoRepository.findAll();

    }


}
