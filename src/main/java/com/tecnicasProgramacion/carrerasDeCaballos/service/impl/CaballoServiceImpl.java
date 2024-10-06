package com.tecnicasProgramacion.carrerasDeCaballos.service.impl;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.YaExisteElCaballoException;
import com.tecnicasProgramacion.carrerasDeCaballos.repository.CaballoRepository;
import com.tecnicasProgramacion.carrerasDeCaballos.service.CaballoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CaballoServiceImpl implements CaballoService {

    @Autowired
    private CaballoRepository caballoRepository;

    @Override
    public Caballo crearCaballo(String nombre, float peso, float altura, int edad) {

        Caballo caballo = new Caballo(nombre, peso, altura, edad);

        if (caballoRepository.findByNombre(nombre).isPresent()) {
            throw new YaExisteElCaballoException();
        }

        caballoRepository.save(caballo);

        return caballo;
    }

    @Override
    public Optional<Caballo> recuperarCaballo(String nombre) {
        return caballoRepository.findByNombre(nombre);
    }

    @Override
    public void removeAll() {
        caballoRepository.deleteAll();
    }
}
