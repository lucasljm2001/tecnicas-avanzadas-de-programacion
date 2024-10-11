package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class NombreInvalidoException extends RuntimeException{

    @Override
    public String getMessage() {
        return "El nombre de la carrera no puede ser vacio";
    }
}
