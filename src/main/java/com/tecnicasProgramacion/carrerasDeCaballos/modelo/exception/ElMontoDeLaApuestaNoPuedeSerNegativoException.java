package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class ElMontoDeLaApuestaNoPuedeSerNegativoException extends RuntimeException{
    @Override
    public String getMessage() {
        return "El monto de la apuesta no puede ser menor o igual a cero";
    }
}
