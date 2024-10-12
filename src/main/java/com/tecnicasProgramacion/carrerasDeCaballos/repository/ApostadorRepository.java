package com.tecnicasProgramacion.carrerasDeCaballos.repository;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ApostadorRepository extends JpaRepository<Apostador, Long> {
    Optional<Apostador> findByDni(String dni);

    Optional<Apostador> findByNombre(String nombre);
}
