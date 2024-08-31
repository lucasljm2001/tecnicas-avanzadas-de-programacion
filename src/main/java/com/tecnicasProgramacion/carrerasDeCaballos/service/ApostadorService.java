package com.tecnicasProgramacion.carrerasDeCaballos.service;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;

public interface ApostadorService {
    public Apostador crearApostador(String dni,String clave, boolean esAdmin);

}
