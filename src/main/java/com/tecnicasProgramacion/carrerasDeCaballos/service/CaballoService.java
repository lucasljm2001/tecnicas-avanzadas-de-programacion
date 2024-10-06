package com.tecnicasProgramacion.carrerasDeCaballos.service;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;

import java.util.Optional;

public interface CaballoService {
    public Caballo crearCaballo(String nombre, float peso, float altura, int edad);

    public Optional<Caballo> recuperarCaballo(String nombre);

    public void removeAll();
}
