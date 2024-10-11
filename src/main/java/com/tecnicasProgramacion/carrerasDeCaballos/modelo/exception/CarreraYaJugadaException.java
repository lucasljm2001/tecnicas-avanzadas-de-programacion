package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class CarreraYaJugadaException extends RuntimeException{

    @Override
    public String getMessage() {
        return "La carrera ya fue definida";
    }
}
