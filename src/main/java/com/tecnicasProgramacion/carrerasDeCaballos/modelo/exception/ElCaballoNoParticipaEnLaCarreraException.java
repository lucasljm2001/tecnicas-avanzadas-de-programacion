package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class ElCaballoNoParticipaEnLaCarreraException extends RuntimeException{

    @Override
    public String getMessage() {
        return "El caballo no participa en la carrera";
    }
}
