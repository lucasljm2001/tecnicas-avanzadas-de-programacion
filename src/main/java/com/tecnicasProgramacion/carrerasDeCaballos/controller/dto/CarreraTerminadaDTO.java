package com.tecnicasProgramacion.carrerasDeCaballos.controller.dto;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import lombok.Getter;

public class CarreraTerminadaDTO {
    @Getter
    private String nombre;
    @Getter
    private CaballoDTO ganador;
    @Getter
    private CaballoDTO segundo;

    public CarreraTerminadaDTO(Carrera carrera) {
        this.nombre = carrera.getNombre();
        this.ganador = new CaballoDTO(carrera.getGanador());
        this.segundo = new CaballoDTO(carrera.getSegundo());
    }
}
