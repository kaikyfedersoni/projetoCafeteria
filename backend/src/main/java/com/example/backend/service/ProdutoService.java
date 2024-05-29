package com.example.backend.service;

import com.example.backend.model.Produto;
import com.example.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoService() {

    }

    public void salvar(Produto produto) {
        boolean existe = produtoRepository.existsById(produto.getId());
        if (existe) {
            throw new RuntimeException("Driver already exists");
        }
        produtoRepository.save(produto);
    }

    public void excluir(Produto produto) {
        produtoRepository.delete(produto);
    }

    public void atualizar(Produto produtoSelecionado, Long idProduto ) {

        Produto produtoAtual = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoAtual.setNome(produtoSelecionado.getNome());
        produtoAtual.setDescricao(produtoSelecionado.getDescricao());
        produtoAtual.setPreco(produtoSelecionado.getPreco());
        produtoRepository.save(produtoAtual);
    }
    public List<Produto> listar(){
        List<Produto> produtos = produtoRepository.findAll();

        return produtos;
    }


}
