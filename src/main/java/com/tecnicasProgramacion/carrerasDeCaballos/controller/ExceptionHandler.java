package com.tecnicasProgramacion.carrerasDeCaballos.controller;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.ApuestaInexistenteException;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.ElDniDebSerNumericoException;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.YaExisteElApostadorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> handleExisteElApostador(YaExisteElApostadorException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> handleElDniNoEsNUmerico(ElDniDebSerNumericoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> handleApuestaInexistente(ApuestaInexistenteException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);


    }
}
