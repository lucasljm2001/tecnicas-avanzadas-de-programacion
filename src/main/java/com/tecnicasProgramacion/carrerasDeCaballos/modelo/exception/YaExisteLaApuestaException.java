package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class YaExisteLaApuestaException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Ya hay una apuesta de este apostador en esta carrera";
    }
}
