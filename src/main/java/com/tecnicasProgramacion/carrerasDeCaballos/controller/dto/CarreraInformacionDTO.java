package com.tecnicasProgramacion.carrerasDeCaballos.controller.dto;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CarreraInformacionDTO {

    private LocalDateTime fechaYHora;
    private int distancia;
    private List<CaballoDTO> competidores;
    private String nombre;


    public CarreraInformacionDTO(Carrera carrera){
        this.fechaYHora = carrera.getFechaYHora();
        this.distancia = carrera.getDistancia();
        this.nombre = carrera.getNombre();
        this.competidores = carrera.getCompetidores().stream().map(caballo -> {
            return new CaballoDTO(caballo, carrera);
        }).toList();

    }

    public CarreraInformacionDTO(){}


}
