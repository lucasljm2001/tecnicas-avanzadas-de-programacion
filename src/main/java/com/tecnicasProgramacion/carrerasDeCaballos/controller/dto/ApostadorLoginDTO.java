package com.tecnicasProgramacion.carrerasDeCaballos.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class ApostadorLoginDTO {

    @Getter
    @Pattern(regexp = "\\d+", message = "El DNI solo debe contener números")
    @NotNull
    @NotBlank
    private String dni;
    @Getter
    @NotNull
    @NotBlank
    private String clave;
    @Getter
    @NotNull
    @NotBlank
    private String nombre;

    private boolean esAdmin;

    public ApostadorLoginDTO() {

    }

    public ApostadorLoginDTO(String dni, String nombre, String clave) {
        this.dni = dni;
        this.nombre = nombre;
        this.clave = clave;
        this.esAdmin = false;
    }

    public boolean getEsAdmin() {
        return esAdmin;
    }
}
