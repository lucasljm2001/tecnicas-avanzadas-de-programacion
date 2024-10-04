package com.tecnicasProgramacion.carrerasDeCaballos.service.impl;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.CarreraDeObstaculos;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.CarreraNormal;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.TipoDeCarrera;
import com.tecnicasProgramacion.carrerasDeCaballos.repository.CarreraRepository;
import com.tecnicasProgramacion.carrerasDeCaballos.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    CarreraRepository carreraRepository;

    @Override
    public List<Carrera> obtenerCarrerasDisponibles(int pagina, int cantidadPorPagina) {
        Pageable page = PageRequest.of(pagina, cantidadPorPagina);
        return carreraRepository.obtenerCarrerasDisponibles(LocalDateTime.now(), page).stream().toList();
    }

    @Override
    public Carrera crearCarrera(LocalDateTime fechaYHora, int distancia, String nombre, TipoDeCarrera tipoCarrera) {
        if (tipoCarrera.equals(TipoDeCarrera.CARRERA_NORMAL)) {
            return carreraRepository.save(new CarreraNormal(fechaYHora, distancia, nombre));
        }
        return carreraRepository.save(new CarreraDeObstaculos(fechaYHora, distancia, nombre));
    }

    @Override
    public Carrera agregarCaballo(Carrera carrera, Caballo caballo) {
        carrera.agregarCompetidor(caballo);
        carreraRepository.save(carrera);
        return carrera;
    }

    @Override
    public void removeAll() {
        carreraRepository.deleteAll();
    }


}
