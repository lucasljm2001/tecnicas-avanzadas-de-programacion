package com.tecnicasProgramacion.carrerasDeCaballos.modelo.caballo;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;

public class CaballoArabe extends Caballo {
    @Override
    public boolean esDeRaza(String raza) {
        return raza.equals("Arabe");
    }

    @Override
    public float velocidad() {
        return 50 / (this.getPeso() * 0.8f);
    }

    @Override
    public float velocidadConObstaculos() {
        return this.velocidad() - (0.3f * this.getAltura());
    }
}
