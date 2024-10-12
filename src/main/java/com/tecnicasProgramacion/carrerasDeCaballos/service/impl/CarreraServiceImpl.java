package com.tecnicasProgramacion.carrerasDeCaballos.service.impl;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.CarreraDeObstaculos;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.CarreraNormal;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.TipoDeCarrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.*;
import com.tecnicasProgramacion.carrerasDeCaballos.repository.CarreraRepository;
import com.tecnicasProgramacion.carrerasDeCaballos.service.CaballoService;
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
        // DEBERIA VALIDAR QUE UNA CARRERA NO SE CREE CON FECHA ANTES DE HOY? EN ESE CASO ME COMPLICARIA LAS PRUEBAS, PREGUNTAR
        if (distancia <= 0) throw new DistanciaInvalidaException();
        if ( nombre==null  || nombre.isBlank()) throw new NombreInvalidoException();
        if (fechaYHora == null) throw new FechaInvalidaException();
        if (tipoCarrera != TipoDeCarrera.CARRERA_DE_OBSTACULOS && tipoCarrera != TipoDeCarrera.CARRERA_NORMAL) {
            throw new CarreraInexistenteException();
        }
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
        Optional<Carrera> carreraRecuperada = carreraRepository.findById(carrera.getId());
        if (carreraRecuperada.isEmpty()) throw new NoExisteLaCarreraException();
        if (carreraRecuperada.get().getGanador()!=null) throw new CarreraYaJugadaException();
        carreraRecuperada.get().agregarCompetidor(caballo);
        carreraRepository.saveAndFlush(carreraRecuperada.get());
        return carreraRecuperada.get();
    }

    @Override
    public Optional<Carrera> recuperarCarrera(String nombre) {
        Optional<Carrera> carreraRecuperada = carreraRepository.findByNombre(nombre);
        if (carreraRecuperada.isEmpty()) throw new NoExisteLaCarreraException();
        return carreraRecuperada;
    }

    @Override
    public Carrera iniciarCarrera(Carrera carrera) {
        // PREGUNTAR SI TIENE SENTIDO QUE LA CARRERA INICIE SIN APUESTAS
        if (carrera.getGanador()!=null) throw new CarreraYaJugadaException();
        carrera.determinarPosicones();
        return carreraRepository.save(carrera);
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
