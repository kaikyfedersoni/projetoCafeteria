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
@RequestMapping("/atendente")
public class AtendenteController {
    private final AtendenteRepository atendenteRepository;

    private final GerenteRepository gerenteRepository;

    public AtendenteController(AtendenteRepository atendenteRepository, GerenteRepository gerenteRepository) {
        this.atendenteRepository = atendenteRepository;
        this.gerenteRepository = gerenteRepository;
    }

    @PostMapping
    public Atendente salvarAtendente(@RequestBody Atendente atendente, @RequestParam Long gerenteId) {
        Gerente gerente = gerenteRepository.findById(gerenteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gerente not found for this id :: " + gerenteId));
        atendente.setGerente(gerente);
        return atendenteRepository.save(atendente);
    }

    @PutMapping("/{id}")
    public Atendente atualizarAtendente(@RequestBody Atendente novoAtendente, @PathVariable Long id) {
        return atendenteRepository.findById(id)
                .map(atendente -> {
                    atendente.setNome(novoAtendente.getNome());
                    atendente.setCpf(novoAtendente.getCpf());
                    atendente.setLogin(novoAtendente.getLogin());
                    atendente.setSenha(novoAtendente.getSenha());

                    if (novoAtendente.getGerente() != null) {
                        Gerente gerente = gerenteRepository.findById(novoAtendente.getGerente().getId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gerente not found for this id :: " + novoAtendente.getGerente().getId()));
                        atendente.setGerente(gerente);
                    }

                    return atendenteRepository.save(atendente);
                })
                .orElseGet(() -> {
                    if (novoAtendente.getGerente() != null) {
                        Gerente gerente = gerenteRepository.findById(novoAtendente.getGerente().getId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gerente not found for this id :: " + novoAtendente.getGerente().getId()));
                        novoAtendente.setGerente(gerente);
                    }

                    novoAtendente.setId(id);
                    return atendenteRepository.save(novoAtendente);
                });
    }

    @DeleteMapping("/{id}")
    public void excluirAtendente(@PathVariable Long id) {
        Atendente atendente = atendenteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Atendente not found for this id :: " + id));
        atendenteRepository.delete(atendente);
    }

    @GetMapping
    public List<Atendente> listarAtendente() {
        return atendenteRepository.findAll();
    }
}
