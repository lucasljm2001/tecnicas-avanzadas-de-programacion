package com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class CarreraNormal  extends Carrera {

    public CarreraNormal(LocalDateTime fechaYHora, int id, String raza, int distancia, Set<Caballo> competidores, Set<Apuesta> apuestas) {
        super(fechaYHora, id, raza, distancia, competidores, apuestas);
    }

    @Override
    public double totalDeVelocidad() {
        return this.getCompetidores().stream().mapToDouble(Caballo::velocidad).sum();
    }

    @Override
    public double velocidadDelCaballo(Caballo caballo) {
        return caballo.velocidad();
    }
}
