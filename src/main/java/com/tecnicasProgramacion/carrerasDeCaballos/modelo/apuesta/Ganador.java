package com.tecnicasProgramacion.carrerasDeCaballos.modelo.apuesta;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;

import java.util.List;

public class Ganador  extends Apuesta {
    public Ganador(float monto, Apostador apostador, Caballo caballo) {
        super(monto, apostador, caballo);
    }

    @Override
    public float montoGanado(Carrera carrera) {
        List<Caballo> caballos = carrera.getPosiciones();
        if (caballos.get(0).getNombre().equals(this.getCaballo().getNombre()) ) {
            return this.getMonto();
        }
        return 0;
    }
}