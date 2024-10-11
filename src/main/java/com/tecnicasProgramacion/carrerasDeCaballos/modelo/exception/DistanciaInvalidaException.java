package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class DistanciaInvalidaException extends RuntimeException{

    @Override
    public String getMessage() {
        return "La distancia de una carrera no puede ser menor o igual a cero";
    }
}
