package com.tecnicasProgramacion.carrerasDeCaballos.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.TipoDeCarrera;
import lombok.Getter;

import java.time.LocalDateTime;

public class CarreraDTO {
    @Getter
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaYHora;
    @Getter
    private int distancia;
    @Getter
    private String nombre;
    @Getter
    private TipoDeCarrera tipoCarrera;

    public CarreraDTO(LocalDateTime fechaYHora, int distancia, String nombre, TipoDeCarrera tipoCarrera){
        this.fechaYHora = fechaYHora;
        this.distancia = distancia;
        this.nombre = nombre;
        this.tipoCarrera = tipoCarrera;
    }

    public CarreraDTO(Carrera carrera){
        this.fechaYHora = carrera.getFechaYHora();
        this.distancia = carrera.getDistancia();
        this.nombre = carrera.getNombre();
        this.tipoCarrera = carrera.getTipoCarrera();
    }
}
