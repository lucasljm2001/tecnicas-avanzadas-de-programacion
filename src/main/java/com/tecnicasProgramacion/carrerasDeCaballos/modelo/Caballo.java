package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Caballo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(unique = true)
    private String nombre;
    @Getter
    private float peso;
    @Setter
    @Getter
    private float altura;
    @Getter
    @Setter
    private int edad;



    public Caballo(String nombre, float peso, float altura, int edad) {
        this.nombre = nombre;
        this.peso = peso;
        this.altura = altura;
        this.edad = edad;
    }

    public Caballo() {
    }

    public void inscribir(Carrera carrera) {
        carrera.getCompetidores().add(this);
    }


    public float velocidad(){
        return 40 / ((peso+altura+edad)*0.25f);
    }

    public float velocidadConObstaculos(){
        return velocidad()/1.6f;
    }


}
