package com.tecnicasProgramacion.carrerasDeCaballos.modelo.caballo;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;

public class PuraSangre extends Caballo {
    @Override
    public boolean esDeRaza(String raza) {
        return raza.equals("Pura Sangre");
    }

    @Override
    public float velocidad() {
        return 20 / ((this.getAltura()  + this.getPeso()) * 0.4f);
    }

    @Override
    public float velocidadConObstaculos() {
        return this.velocidad();
    }
}
