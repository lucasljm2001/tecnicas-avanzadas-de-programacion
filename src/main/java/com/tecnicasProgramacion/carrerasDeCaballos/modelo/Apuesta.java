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
    @OneToOne
    private Apostador apostador;
    @Getter
    @OneToOne
    private Caballo caballo;

    public Apuesta(float monto, Apostador apostador, Caballo caballo) {
        this.monto = monto;
        this.apostador = apostador;
        this.caballo = caballo;
    }

    public Apuesta() {

    }

    public abstract float montoGanado(Carrera carrera);

}
