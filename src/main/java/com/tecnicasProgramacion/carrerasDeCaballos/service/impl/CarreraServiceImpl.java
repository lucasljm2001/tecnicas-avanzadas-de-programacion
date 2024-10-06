package com.tecnicasProgramacion.carrerasDeCaballos.service.impl;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.CarreraDeObstaculos;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.CarreraNormal;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.TipoDeCarrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.YaExisteLaCarreraException;
import com.tecnicasProgramacion.carrerasDeCaballos.repository.CarreraRepository;
import com.tecnicasProgramacion.carrerasDeCaballos.service.CarreraService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        if (carreraRepository.findByNombre(nombre).isPresent()) {
            throw new YaExisteLaCarreraException();
        }
        if (tipoCarrera.equals(TipoDeCarrera.CARRERA_NORMAL)) {
            return carreraRepository.save(new CarreraNormal(fechaYHora, distancia, nombre));
        }
        return carreraRepository.save(new CarreraDeObstaculos(fechaYHora, distancia, nombre));
    }

    @Override
    @Transactional
    public Carrera agregarCaballo(Carrera carrera, Caballo caballo) {
        Carrera carreraRecuperada = carreraRepository.findById(carrera.getId()).get();
        carreraRecuperada.agregarCompetidor(caballo);
        carreraRepository.saveAndFlush(carreraRecuperada);
        return carreraRecuperada;
    }

    @Override
    public Optional<Carrera> recuperarCarrera(String nombre) {
        return carreraRepository.findByNombre(nombre);
    }

    @Override
    public void modificarCarrera(Carrera carrera) {
        carreraRepository.save(carrera);
    }

    @Override
    public void removeAll() {
        carreraRepository.deleteAll();
    }


}
