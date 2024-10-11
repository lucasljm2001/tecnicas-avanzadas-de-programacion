package com.tecnicasProgramacion.carrerasDeCaballos.modelo.apuesta;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Ganador  extends Apuesta {



    public Ganador(float monto, Apostador apostador, Caballo caballo, Carrera carrera) {
        super(monto, apostador, caballo, carrera);
    }

    public Ganador() {
    }


    @Override
    public float montoGanado(Carrera carrera) {
        if (carrera.getGanador().getNombre().equals(this.getCaballo().getNombre()) ) {
            return this.getMonto();
        }
        return 0;
    }
}
