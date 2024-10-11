package com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class CarreraNormal  extends Carrera {

    public CarreraNormal(LocalDateTime fechaYHora, int distancia, String nombre) {
        super(fechaYHora,distancia, nombre);
    }

    public CarreraNormal() {

    }

    @Override
    public double totalDeVelocidad() {
        return this.getCompetidores().stream().mapToDouble(Caballo::velocidad).sum();
    }

    @Override
    public double velocidadDelCaballo(Caballo caballo) {
        return caballo.velocidad();
    }

    @Override
    public TipoDeCarrera getTipoCarrera() {
        return TipoDeCarrera.CARRERA_DE_OBSTACULOS;
    }
}
