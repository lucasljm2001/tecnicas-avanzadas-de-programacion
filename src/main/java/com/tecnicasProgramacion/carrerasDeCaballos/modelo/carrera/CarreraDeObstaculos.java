package com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;

import java.time.LocalDateTime;
import java.util.Set;

public class CarreraDeObstaculos  extends Carrera {

    public CarreraDeObstaculos(LocalDateTime fechaYHora, int id, String raza, int distancia, Set<Caballo> competidores, Set<Apuesta> apuestas) {
        super(fechaYHora, id, raza, distancia, competidores, apuestas);
    }

    @Override
    public double totalDeVelocidad() {
        return this.getCompetidores().stream().mapToDouble(Caballo::velocidadConObstaculos).sum();
    }

    @Override
    public double velocidadDelCaballo(Caballo caballo) {
        return caballo.velocidadConObstaculos();
    }
}
