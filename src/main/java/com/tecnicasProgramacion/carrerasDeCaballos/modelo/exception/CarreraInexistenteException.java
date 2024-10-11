package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class CarreraInexistenteException extends RuntimeException{

    @Override
    public String getMessage() {
        return "No existe ese tipo de carrera";
    }
}
