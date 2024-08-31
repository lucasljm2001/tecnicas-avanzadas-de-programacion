package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class YaExisteElApostadorException  extends RuntimeException{
    @Override
    public String getMessage() {
        return "El apostador ya existe";
    }
}
