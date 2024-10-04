package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Carrera {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    @Getter
    @Setter
    private LocalDateTime fechaYHora;
    @Getter
    @Setter
    @ManyToMany
    private List<Caballo> posiciones;
    @Getter
    @ManyToMany
    private Set<Caballo> competidores;

    @Getter
    @Setter
    private int distancia;
    @Getter
    @OneToMany
    private Set<Apuesta> apuestas;

    public Carrera(LocalDateTime fechaYHora, int distancia, Set<Caballo> competidores, Set<Apuesta> apuestas, String nombre) {
        this.fechaYHora = fechaYHora;
        this.distancia = distancia;
        this.posiciones = new ArrayList<Caballo>();
        this.competidores = competidores;
        this.apuestas = apuestas;
        this.nombre = nombre;
    }

    public Carrera(LocalDateTime fechaYHora, int distancia, String nombre) {
        this.fechaYHora = fechaYHora;
        this.distancia = distancia;
        this.posiciones = new ArrayList<Caballo>();
        this.competidores = new HashSet<Caballo>();
        this.apuestas = new HashSet<Apuesta>();
        this.nombre = nombre;
    }

    public Carrera() {

    }

    public void agregarCompetidor(Caballo caballo){
        competidores.add(caballo);
    }

    public abstract double totalDeVelocidad();

    public abstract double velocidadDelCaballo(Caballo caballo);

    public void determinarPosicones(){
        List<Caballo> caballosList = new ArrayList<>(competidores);
        List<Caballo> posicionesAsignadas = new ArrayList<>();

        Random random = new Random();

        int posicion = 1;
        while (!caballosList.isEmpty()) {
            double totalVelocidad = this.totalDeVelocidad();

            double randomValue = random.nextDouble() * totalVelocidad;

            double acumulado = 0;
            Caballo caballoElegido = null;
            for (Caballo caballo : caballosList) {
                acumulado += this.velocidadDelCaballo(caballo);
                if (acumulado >= randomValue) {
                    caballoElegido = caballo;
                    break;
                }
            }

            if (caballoElegido != null) {
                posicionesAsignadas.add(caballoElegido);
                caballosList.remove(caballoElegido);
            }



        }
        this.setPosiciones(posicionesAsignadas);
    }

    public boolean esCarreraIniciada(){
        return this.fechaYHora.isBefore(LocalDateTime.now());
    }

    private long apuestasAlMismoCaballo(Caballo caballo){
        return apuestas.stream().filter(apuesta1 -> apuesta1.getCaballo().getNombre().equals(caballo.getNombre())).count();
    }

    public float porcentajeDePago(Caballo caballo){
        long apuestasAlMismoCaballo = apuestasAlMismoCaballo(caballo);
        return  1 -  (0.05f * apuestasAlMismoCaballo); //  PREGUNTAR, ESTE CALCULO NO ES MUY REAL
    }

    public void premio(Apuesta apuesta){

        Apostador apostador = apuesta.getApostador();

        float montoASumar = apuesta.montoGanado(this) - porcentajeDePago(apuesta.getCaballo());

        apostador.setMontoAcumulado(apostador.getMontoAcumulado() + montoASumar);
    }



    public void setId(int id) {
        this.id = id;
    }


}
