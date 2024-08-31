package com.tecnicasProgramacion.carrerasDeCaballos.controller.dto;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import lombok.Getter;

public class ApostadorDTO {

    @Getter
    private String dni;
    @Getter
    private float montoAcumulado;

    public ApostadorDTO() {

    }

    public ApostadorDTO(Apostador apostador) {
        this.dni = apostador.getDni();
        this.montoAcumulado = apostador.getMontoAcumulado();
    }
}
