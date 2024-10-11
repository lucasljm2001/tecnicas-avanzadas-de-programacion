package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class NoExisteElCaballoException extends RuntimeException{

    @Override
    public String getMessage() {
        return "No existe el caballo";
    }
}
