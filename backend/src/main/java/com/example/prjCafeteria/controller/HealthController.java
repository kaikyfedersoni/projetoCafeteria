package com.example.prjCafeteria.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/hello")
    public String health(){
        return "Olá mundo 😁😁😁";
    }

}