package com.tecnicasProgramacion.carrerasDeCaballos.controller.dto;

import lombok.Getter;

public class ApostadorLoginDTO {

    @Getter
    private String dni;
    @Getter
    private String clave;

    private boolean esAdmin;

    public ApostadorLoginDTO() {

    }

    public ApostadorLoginDTO(String dni, String clave) {
        this.dni = dni;
        this.clave = clave;
        this.esAdmin = false;
    }

    public boolean getEsAdmin() {
        return esAdmin;
    }
}
