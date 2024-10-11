package com.tecnicasProgramacion.carrerasDeCaballos.controller.dto;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import lombok.Getter;

import java.util.List;

public class CarreraConCaballosDTO {
    @Getter
    private String nombre;
    @Getter
    private List<CaballoDTO> caballos;

    public CarreraConCaballosDTO(Carrera carrera) {
        this.nombre = carrera.getNombre();
        this.caballos = carrera.getCompetidores().stream().map(CaballoDTO::new).toList();
    }
}
