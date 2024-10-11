package com.tecnicasProgramacion.carrerasDeCaballos.service.impl;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import com.tecnicasProgramacion.carrerasDeCaballos.repository.ApuestaRepository;
import com.tecnicasProgramacion.carrerasDeCaballos.service.ApuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApuestaServiceImpl implements ApuestaService {

    @Autowired
    private ApuestaRepository apuestaRepository;

    @Override
    public Apuesta crearApuesta(Apuesta apuesta) {
        return apuestaRepository.save(apuesta);
    }

    @Override
    public void removeAll() {
        apuestaRepository.deleteAll();
    }
}
