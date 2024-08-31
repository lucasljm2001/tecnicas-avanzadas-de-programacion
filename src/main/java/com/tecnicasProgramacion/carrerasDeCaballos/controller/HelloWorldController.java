package com.tecnicasProgramacion.carrerasDeCaballos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hola Mundo!";
    }
    @GetMapping("/autorizado")
    public String autorizado() {
        return "Autorizado!";
    }
}

