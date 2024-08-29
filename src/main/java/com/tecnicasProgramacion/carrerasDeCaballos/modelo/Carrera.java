package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Carrera {

    private LocalDateTime fechaYHora;
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

    public abstract void determinarPosicones();

    public boolean esCarreraIniciada(){
        return this.fechaYHora.isBefore(LocalDateTime.now());
    }

    public float premio(Apuesta apuesta){
        return 0;
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
