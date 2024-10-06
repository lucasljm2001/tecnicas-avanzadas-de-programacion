package com.tecnicasProgramacion.carrerasDeCaballos.repository;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApuestaRepository extends JpaRepository<Apuesta, Long> {
}
