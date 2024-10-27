package com.tecnicasProgramacion.carrerasDeCaballos.controller.dto;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.apuesta.EstadoApuesta;
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
    @Getter
    private String estado;

    public ApuestaDTO(String tipo, float monto, String caballo, String carrera) {
        this.tipo = tipo;
        this.monto = monto;
        this.caballo = caballo;
        this.carrera = carrera;
        this.estado = EstadoApuesta.EnCurso.toString();
    }

    public ApuestaDTO(Apuesta apuesta, String carrera) {
        this(apuesta.getClass().getSimpleName(), apuesta.getMonto(), apuesta.getCaballo().getNombre(), carrera);
    }

    public ApuestaDTO() {
    }

    public ApuestaDTO(Apuesta apuesta) {
        this.tipo = apuesta.getClass().getSimpleName();
        this.monto = apuesta.getMonto();
        this.caballo = apuesta.getCaballo().getNombre();
        this.carrera = apuesta.getCarrera().getNombre();
        this.estado = apuesta.getCarrera().getEstadoApuesta(apuesta).toString();
    }


}
