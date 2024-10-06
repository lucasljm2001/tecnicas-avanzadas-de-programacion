package com.tecnicasProgramacion.carrerasDeCaballos.service;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;

import java.util.Optional;

public interface ApostadorService {
    public Apostador crearApostador(String dni,String clave, String nombre, boolean esAdmin);

    public void removeAll();

    public Optional<Apostador> recuperarApostador(String dni);

    public Apuesta apostar(String tipo, float monto, String caballo, String carrera);
}
