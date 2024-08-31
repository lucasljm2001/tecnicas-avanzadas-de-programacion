package com.tecnicasProgramacion.carrerasDeCaballos.modelo.apuesta;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;

import java.util.List;

public class Segundo extends Apuesta {
    public Segundo(float monto, Apostador apostador, Caballo caballo) {
        super(monto, apostador, caballo);
    }

    @Override
    public float montoGanado(Carrera carrera) {
        List<Caballo> caballos = carrera.getPosiciones();
        if (caballos.subList(0, 1).stream().map(Caballo::getNombre).toList().contains(this.getCaballo().getNombre()) ) {
            return this.getMonto() / 2;
        }
        return 0;
    }
}
