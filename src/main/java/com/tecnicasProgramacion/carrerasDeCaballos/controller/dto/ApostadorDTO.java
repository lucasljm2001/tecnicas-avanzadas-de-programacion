package com.tecnicasProgramacion.carrerasDeCaballos.controller.dto;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import lombok.Getter;

import java.util.List;

public class ApostadorDTO {

    @Getter
    private String dni;
    @Getter
    private float montoAcumulado;
    @Getter
    private List<ApuestaDTO> apuestas;

    public ApostadorDTO() {

    }

    public ApostadorDTO(Apostador apostador) {
        this.dni = apostador.getDni();
        this.montoAcumulado = apostador.getMontoAcumulado();
        this.apuestas = apostador.getApuestas().stream().map(ApuestaDTO::new).toList();
    }
}
