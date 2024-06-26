package com.example.prjCafeteria.controller;

import com.example.prjCafeteria.repository.FuncionarioRepository;

import org.springframework.web.bind.annotation.*;
import com.example.prjCafeteria.model.Funcionario;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }

    @PostMapping
    public Funcionario inserir(@RequestBody Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Funcionario atualizar(@RequestBody Funcionario novoFuncionario, @PathVariable Long id) {
        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionario.setNome(novoFuncionario.getNome());
                    funcionario.setCpf(novoFuncionario.getCpf());
                    funcionario.setLogin(novoFuncionario.getLogin());
                    return funcionarioRepository.save(funcionario);
                })
                .orElseGet(() -> {
                    novoFuncionario.setId(id);
                    return funcionarioRepository.save(novoFuncionario);
                });
    }

    @GetMapping("/{id}")
    public Optional<Funcionario> buscarPorId(@PathVariable Long id) {
        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionario.setSenha("");
                    return Optional.of(funcionario);
                })
                .orElseGet(Optional::empty);
    }

    @GetMapping
    public List<Funcionario> all() {
        return funcionarioRepository.findAll();
    }
}
