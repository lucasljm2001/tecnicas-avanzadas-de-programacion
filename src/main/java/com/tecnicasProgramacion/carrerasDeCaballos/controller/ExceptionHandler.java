package com.tecnicasProgramacion.carrerasDeCaballos.controller;

import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.ErrorDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorDTO> handleExisteElApostador(YaExisteElApostadorException e) {
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorDTO> handleElDniNoEsNUmerico(ElDniDebSerNumericoException e) {
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorDTO> handleApuestaInexistente(ApuestaInexistenteException e) {
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorDTO> handleNoHaySuficientesCaballos(NoHaySuficientesCaballosException e) {
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorDTO> handleElCaballoNoEstaEnLaCarrera(ElCaballoNoParticipaEnLaCarreraException e) {
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorDTO> handleCarreraIniciada(LaCarreraYaInicioException e) {
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
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
