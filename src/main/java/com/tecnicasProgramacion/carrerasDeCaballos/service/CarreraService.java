package com.tecnicasProgramacion.carrerasDeCaballos.service;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.TipoDeCarrera;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface CarreraService {

    public List<Carrera> obtenerCarrerasDisponibles(int pagina, int cantidadPorPagina);

    public Carrera crearCarrera(LocalDateTime fechaYHora, int distancia, String nombre, TipoDeCarrera tipoCarrera);

    public Carrera agregarCaballo(Carrera carrera, Caballo caballo);

    public void removeAll();
}
