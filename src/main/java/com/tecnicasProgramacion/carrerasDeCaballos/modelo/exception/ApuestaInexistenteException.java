package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class ApuestaInexistenteException  extends RuntimeException{

    @Override
    public String getMessage() {
        return "La Apuesta no existe";
    }
}
