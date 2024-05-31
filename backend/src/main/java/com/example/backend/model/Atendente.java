package com.example.backend.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Atendente")
public class Atendente extends Funcionario{

    @ManyToOne
    @JoinColumn(name ="gerente_id")
    private Gerente gerente;

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }
}
