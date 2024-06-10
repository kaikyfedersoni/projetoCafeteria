package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Gerente {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Funcionario funcionario;

}
