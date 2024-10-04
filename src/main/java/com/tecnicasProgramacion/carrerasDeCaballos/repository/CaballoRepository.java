package com.tecnicasProgramacion.carrerasDeCaballos.repository;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaballoRepository extends JpaRepository<Caballo, Long> {
    public Optional<Caballo> findByNombre(String nombre);
}
