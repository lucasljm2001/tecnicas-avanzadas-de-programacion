package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Carrera {

    private LocalDateTime fechaYHora;
    @Setter
    private List<Caballo> posiciones;
    private Set<Caballo> competidores;
    private int id;
    private String raza;
    private int distancia;
    private Set<Apuesta> apuestas;

    public Carrera(LocalDateTime fechaYHora, int id, String raza, int distancia, Set<Caballo> competidores, Set<Apuesta> apuestas) {
        this.fechaYHora = fechaYHora;
        this.id = id;
        this.raza = raza;
        this.distancia = distancia;
        this.posiciones = new ArrayList<Caballo>();
        this.competidores = competidores;
        this.apuestas = apuestas;
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

    public void premio(Apuesta apuesta){
        long apuestasAlMismoCaballos = apuestas.stream().filter(apuesta1 -> apuesta1.getCaballo().getNombre().equals(apuesta.getCaballo().getNombre())).count();

        Apostador apostador = apuesta.getApostador();

        float montoASumar = apuesta.montoGanado(this) * 0.05f * apuestasAlMismoCaballos;

        apostador.setMontoAcumulado(apostador.getMontoAcumulado() + montoASumar);
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public List<Caballo> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<Caballo> posiciones) {
        this.posiciones = posiciones;
    }

    public Set<Caballo> getCompetidores() {
        return competidores;
    }

    public void setCompetidores(Set<Caballo> competidores) {
        this.competidores = competidores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Set<Apuesta> getApuestas() {
        return apuestas;
    }

    public void setApuestas(Set<Apuesta> apuestas) {
        this.apuestas = apuestas;
    }
}
