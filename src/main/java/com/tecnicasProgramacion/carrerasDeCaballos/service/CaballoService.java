package com.tecnicasProgramacion.carrerasDeCaballos.service;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;

public interface CaballoService {
    public Caballo crearCaballo(String nombre, float peso, float altura, int edad);

    public void removeAll();
}
