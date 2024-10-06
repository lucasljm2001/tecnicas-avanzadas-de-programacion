package com.tecnicasProgramacion.carrerasDeCaballos.repository;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    @Query("SELECT c FROM Carrera c WHERE c.fechaYHora > :currentDateTime")
    public List<Carrera> obtenerCarrerasDisponibles(@Param("currentDateTime") LocalDateTime currentDateTime, Pageable pageable);

    public Optional<Carrera> findByNombre(String nombre);
}
