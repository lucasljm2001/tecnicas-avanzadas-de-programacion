package com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class CarreraDeObstaculos  extends Carrera {

    public CarreraDeObstaculos(LocalDateTime fechaYHora, int distancia, String nombre) {
        super(fechaYHora, distancia, nombre);
    }

    public CarreraDeObstaculos() {

    }

    @Override
    public double totalDeVelocidad() {
        return this.getCompetidores().stream().mapToDouble(Caballo::velocidadConObstaculos).sum();
    }

    @Override
    public double velocidadDelCaballo(Caballo caballo) {
        return caballo.velocidadConObstaculos();
    }

    @Override
    public TipoDeCarrera getTipoCarrera() {
        return TipoDeCarrera.CARRERA_NORMAL;
    }
}
