package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class YaExisteElCaballoException extends RuntimeException{
    @Override
    public String getMessage() {
        return "El caballo ya existe";
    }
}
