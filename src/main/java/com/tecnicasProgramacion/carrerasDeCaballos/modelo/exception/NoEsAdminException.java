package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class NoEsAdminException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Solo los administradores pueden modificar carreras";
    }
}
