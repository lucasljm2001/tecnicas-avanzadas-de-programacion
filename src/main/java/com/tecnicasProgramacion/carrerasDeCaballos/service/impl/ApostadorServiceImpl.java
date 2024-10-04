package com.tecnicasProgramacion.carrerasDeCaballos.service.impl;

import com.tecnicasProgramacion.carrerasDeCaballos.repository.ApostadorRepository;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.ElDniDebSerNumericoException;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.YaExisteElApostadorException;
import com.tecnicasProgramacion.carrerasDeCaballos.service.ApostadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApostadorServiceImpl  implements ApostadorService, UserDetailsService {

    @Autowired
    ApostadorRepository apostadorRepository;



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
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        return apostadorRepository.findByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("Apostador no encontrado"));

    }
}
