package com.example.backend.controller;

import com.example.backend.model.Atendente;
import com.example.backend.model.Gerente;
import com.example.backend.repository.AtendenteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendente")
public class AtendenteController {
    private final AtendenteRepository atendenteRepository;

    public AtendenteController(AtendenteRepository atendenteRepository) {
        this.atendenteRepository = atendenteRepository;
    }

    @PostMapping
    public Atendente salvar(@RequestBody Atendente atendente) {
        return atendenteRepository.save(atendente);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        atendenteRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Atendente updateGerente(@PathVariable Long id, @RequestBody Atendente novoAtendente) {
        return atendenteRepository.findById(id)
                .map(atendente -> {
                    atendente.setNome(novoAtendente.getNome());
                    atendente.setCpf(novoAtendente.getCpf());
                    atendente.setLogin(novoAtendente.getLogin());
                    atendente.setSenha(novoAtendente.getSenha());
                    return atendenteRepository.save(atendente);
                })
                .orElseGet(() -> {
                    novoAtendente.setId(id);
                    return atendenteRepository.save(novoAtendente);
                });
    }

    @GetMapping
    public List<Atendente> listar() {
        return atendenteRepository.findAll();
    }
}
