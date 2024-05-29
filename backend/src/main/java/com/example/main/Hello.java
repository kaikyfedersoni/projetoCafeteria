package com.example.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping(value = "/hello", params = "nome")
    public String index(@RequestParam String nome) {
        return "Olá " + nome +  " 😁😁😁";
    }

}