package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class NoExisteLaCarreraException extends RuntimeException{

    @Override
    public String getMessage() {
        return "No existe esa carrera";
    }
}
