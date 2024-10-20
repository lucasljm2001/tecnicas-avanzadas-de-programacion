package com.tecnicasProgramacion.carrerasDeCaballos.modelo.apuesta;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Segundo extends Apuesta {
    public Segundo(float monto, Apostador apostador, Caballo caballo,  Carrera carrera) {
        super(monto, apostador, caballo, carrera);
    }

    public Segundo() {

    }

    @Override
    public float montoGanado(Carrera carrera) {
        Set<String> caballos = new HashSet<>();
        if (carrera.getGanador() == null || carrera.getSegundo() == null) {
            return 0;
        }
        caballos.add(carrera.getGanador().getNombre());
        caballos.add(carrera.getSegundo().getNombre());
        if (caballos.contains(this.getCaballo().getNombre()) ) {
            return this.getMonto() / 2;
        }
        return 0;
    }
}
