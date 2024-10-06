package com.tecnicasProgramacion.carrerasDeCaballos.controller.dto;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import lombok.Getter;

public class ApuestaDTO {
    @Getter
    private String tipo;
    @Getter
    private float monto;
    @Getter
    private String caballo;
    @Getter
    private String carrera;

    public ApuestaDTO(String tipo, float monto, String caballo, String carrera) {
        this.tipo = tipo;
        this.monto = monto;
        this.caballo = caballo;
        this.carrera = carrera;
    }

    public ApuestaDTO(Apuesta apuesta, String carrera) {
        this(apuesta.getClass().getSimpleName(), apuesta.getMonto(), apuesta.getCaballo().getNombre(), carrera);
    }

    public ApuestaDTO() {
    }


}
