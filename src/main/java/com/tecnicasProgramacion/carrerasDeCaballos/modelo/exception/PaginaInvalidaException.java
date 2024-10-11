package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class PaginaInvalidaException extends RuntimeException{

    @Override
    public String getMessage() {
        return "La pagina no puede ser negativa";
    }
}
