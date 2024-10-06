package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class YaExisteLaCarreraException extends RuntimeException{
    @Override
    public String getMessage() {
        return "La carrera ya existe";
    }
}
