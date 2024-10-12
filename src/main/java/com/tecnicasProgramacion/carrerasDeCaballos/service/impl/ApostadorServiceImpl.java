package com.tecnicasProgramacion.carrerasDeCaballos.service.impl;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.*;
import com.tecnicasProgramacion.carrerasDeCaballos.repository.ApostadorRepository;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.service.ApostadorService;
import com.tecnicasProgramacion.carrerasDeCaballos.service.ApuestaService;
import com.tecnicasProgramacion.carrerasDeCaballos.service.CaballoService;
import com.tecnicasProgramacion.carrerasDeCaballos.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ApostadorServiceImpl  implements ApostadorService, UserDetailsService {

    @Autowired
    ApostadorRepository apostadorRepository;

    @Autowired
    CarreraService carreraService;

    @Autowired
    CaballoService caballoService;

    @Autowired
    ApuestaService apuestaService;



    @Override
    public Apostador crearApostador(String dni, String clave, String nombre ,boolean esAdmin) {
        if (apostadorRepository.findByDni(dni).isPresent()) {
            throw new YaExisteElApostadorException();
        }
        try {
            Integer dniNumerico = Integer.parseInt(dni);
            Apostador apostador = new Apostador(dni, clave, nombre, esAdmin);

            apostadorRepository.save(apostador);
            return apostador;
        } catch (NumberFormatException e) {
            throw new ElDniDebSerNumericoException();
        }


    }

    @Override
    public void removeAll() {
        apostadorRepository.deleteAll();
    }

    @Override
    public Optional<Apostador> recuperarApostador(String dni) {
        return apostadorRepository.findByDni(dni);
    }



    @Override
    public Apuesta apostar(String tipo, float monto, String caballo, String carrera) {
        Apostador apostador = apostadorRepository.findByDni(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        Caballo caballoRecuperado = caballoService.recuperarCaballo(caballo).get();
        Carrera carreraRecuperada = carreraService.recuperarCarrera(carrera).get();
        if (tipo==null || tipo.isBlank() || tipo.isEmpty()) throw new ElTipoDeApuestaNoPuedeSerVacioException();
        if (monto <= 0) throw new ElMontoDeLaApuestaNoPuedeSerNegativoException();
        if (carreraRecuperada.getFechaYHora().isBefore(LocalDateTime.now())) throw new LaCarreraYaInicioException();
        if (carreraRecuperada.getCompetidores().stream().noneMatch(caballo1 -> caballo1.getNombre().equals(caballoRecuperado.getNombre()))) throw new ElCaballoNoParticipaEnLaCarreraException();
        if (carreraRecuperada.getApuestas().stream().anyMatch(apuesta -> apuesta.getApostador().getDni().equals(apostador.getDni())))
            throw new YaExisteLaApuestaException() ;
        Apuesta apuesta = apostador.apostar(carreraRecuperada, caballoRecuperado, monto, tipo);
        apuestaService.crearApuesta(apuesta);
        carreraService.modificarCarrera(carreraRecuperada);
        apostadorRepository.save(apostador);
        return apuesta;
    }

    @Override
    public Optional<Apostador> recuperarPorNombre(String nombre) {
        return apostadorRepository.findByNombre(nombre);
    }

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        return apostadorRepository.findByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("Apostador no encontrado"));

    }
}
