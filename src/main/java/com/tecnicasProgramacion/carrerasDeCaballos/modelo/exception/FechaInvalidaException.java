package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class FechaInvalidaException extends RuntimeException{

    @Override
    public String getMessage() {
        return "La fecha de la carrera es invalida";
    }
}
