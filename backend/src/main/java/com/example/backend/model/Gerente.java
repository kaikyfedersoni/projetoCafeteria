package com.example.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("Gerente")
public class Gerente extends Funcionario{

    public Gerente() {
    }

    public Gerente(String cpf, String nome, String senha, String login) {
        super(cpf, nome, senha, login);
    }
}
