package com.tecnicasProgramacion.carrerasDeCaballos.controller.dto;

public class ErrorDTO {

    private String mensaje;

    public ErrorDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
