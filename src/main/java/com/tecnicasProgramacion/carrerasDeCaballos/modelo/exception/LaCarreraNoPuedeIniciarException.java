package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class LaCarreraNoPuedeIniciarException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Aun no ha pasado la hora para determinar el ganador de la carrera";
    }
}
