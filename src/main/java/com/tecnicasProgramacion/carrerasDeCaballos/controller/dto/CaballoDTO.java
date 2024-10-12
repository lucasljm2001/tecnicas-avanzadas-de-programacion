package com.tecnicasProgramacion.carrerasDeCaballos.controller.dto;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import lombok.Getter;

public class CaballoDTO {
    @Getter
    private String nombre;
    @Getter
    private float peso;
    @Getter
    private float altura;
    @Getter
    private int edad;
    @Getter
    private float porcentajeDePago;


    public CaballoDTO(Caballo caballo){
        this.nombre = caballo.getNombre();
        this.peso = caballo.getPeso();
        this.altura = caballo.getAltura();
        this.edad = caballo.getEdad();
    }

    public CaballoDTO(Caballo caballo, Carrera carrera){
        this(caballo);
        porcentajeDePago = carrera.porcentajeDePago(caballo);
    }

    public CaballoDTO(){}

}
