package com.tecnicasProgramacion.carrerasDeCaballos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
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
        return "Hola autorizado!" + SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

