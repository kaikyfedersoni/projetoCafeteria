package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Relatorio {
    @Id @GeneratedValue
    private Long id;
    private double total;


}
