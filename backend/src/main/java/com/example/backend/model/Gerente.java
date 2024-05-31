package com.example.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("Gerente")
public class Gerente extends Funcionario{

    @OneToMany(mappedBy ="gerente",cascade = CascadeType.ALL)
    private List<Atendente> atendentes;

    public List<Atendente> getAtendentes() {
        return atendentes;
    }

    public void setAtendentes(List<Atendente> atendentes) {
        this.atendentes = atendentes;
    }

}
