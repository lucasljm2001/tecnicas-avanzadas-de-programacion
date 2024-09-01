package com.tecnicasProgramacion.carrerasDeCaballos.modelo.caballo;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;

public class StandardBred extends Caballo {
    @Override
    public boolean esDeRaza(String raza) {
        return raza.equals("StandardBred");
    }

    @Override
    public float velocidad() {
        return 40 / ((this.getAltura() + this.getEdad() + this.getPeso()) * 0.25f);
    }

    @Override
    public float velocidadConObstaculos() {
        return this.velocidad() / 1.6f;
    }
}
