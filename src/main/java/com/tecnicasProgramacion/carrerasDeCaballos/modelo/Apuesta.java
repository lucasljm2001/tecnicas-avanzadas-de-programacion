package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class Apuesta {

    @Setter
    @Getter
    private float monto;
    @Getter
    @Setter
    private Apostador apostador;
    @Getter
    private Caballo caballo;

    public Apuesta(float monto, Apostador apostador, Caballo caballo) {
        this.monto = monto;
        this.apostador = apostador;
        this.caballo = caballo;
    }

    public abstract float montoGanado(Carrera carrera);

}
