package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class ElTipoDeApuestaNoPuedeSerVacioException extends RuntimeException{
    @Override
    public String getMessage() {
        return "El tipo de apuesta no puede ser vacio";
    }
}
