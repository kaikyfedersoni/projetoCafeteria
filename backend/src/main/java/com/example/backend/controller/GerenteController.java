package com.example.backend.controller;

import com.example.backend.model.Atendente;
import com.example.backend.model.Gerente;
import com.example.backend.repository.AtendenteRepository;
import com.example.backend.repository.GerenteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/gerente")
public class GerenteController {
    private final GerenteRepository gerenteRepository;
    private final AtendenteRepository atendenteRepository;

    public GerenteController(GerenteRepository gerenteRepository, AtendenteRepository atendenteRepository) {
        this.gerenteRepository = gerenteRepository;
        this.atendenteRepository = atendenteRepository;
    }

    @GetMapping("/{id}/atendentes")
    public List<Atendente> getAtendentesByGerente(@PathVariable Long id) {
        Gerente gerente = gerenteRepository.findById(id).orElse(null);
        return atendenteRepository.findByGerente(gerente);
    }

    @PostMapping
    public Gerente salvarGerente(@RequestBody Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    @PutMapping("/{id}")
    public Gerente atualizarGerente(@PathVariable Long id, @RequestBody Gerente novoGerente) {
        return gerenteRepository.findById(id)
                .map(gerente -> {
                    gerente.setNome(novoGerente.getNome());
                    gerente.setCpf(novoGerente.getCpf());
                    gerente.setLogin(novoGerente.getLogin());
                    gerente.setSenha(novoGerente.getSenha());
                    return gerenteRepository.save(gerente);
                })
                .orElseGet(() -> {
                    novoGerente.setId(id);
                    return gerenteRepository.save(novoGerente);
                });
    }

    @GetMapping
    public List<Gerente> listarGerentes() {
        return gerenteRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void excluirGerente(@PathVariable Long id) {
        Gerente gerente = gerenteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gerente not found for this id :: " + id));
        gerenteRepository.delete(gerente);
    }


}
