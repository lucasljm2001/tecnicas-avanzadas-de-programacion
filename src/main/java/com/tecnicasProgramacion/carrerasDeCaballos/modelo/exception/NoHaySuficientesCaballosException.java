package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class NoHaySuficientesCaballosException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Debe haber al menos dos caballos para comenzar la carrera";
    }
}
