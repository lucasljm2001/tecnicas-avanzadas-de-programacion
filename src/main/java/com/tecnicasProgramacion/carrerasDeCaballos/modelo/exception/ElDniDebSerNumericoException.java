package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class ElDniDebSerNumericoException extends RuntimeException{
    @Override
    public String getMessage() {
        return "El dni debe ser un numero";
    }
}
