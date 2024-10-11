package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Apuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Getter
    private float monto;
    @Getter
    @Setter
    @ManyToOne
    private Apostador apostador;
    @Getter
    @ManyToOne
    private Caballo caballo;
    @Getter
    @ManyToOne
    private Carrera carrera;



    public Apuesta(float monto, Apostador apostador, Caballo caballo, Carrera carrera) {
        this.monto = monto;
        this.apostador = apostador;
        this.caballo = caballo;
        this.carrera = carrera;
    }

    public Apuesta() {

    }

    public abstract float montoGanado(Carrera carrera);

}
