package com.tecnicasProgramacion.carrerasDeCaballos.service.impl;

import com.tecnicasProgramacion.carrerasDeCaballos.Repository.ApostadorRepository;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.YaExisteElApostadorException;
import com.tecnicasProgramacion.carrerasDeCaballos.service.ApostadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApostadorServiceImpl  implements ApostadorService, UserDetailsService {

    @Autowired
    ApostadorRepository apostadorRepository;



    @Override
    public Apostador crearApostador(String dni, String clave, boolean esAdmin) {
        if (apostadorRepository.findByDni(dni).isPresent()) {
            throw new YaExisteElApostadorException();
        }

        Apostador apostador = new Apostador(dni, clave, esAdmin);

        apostadorRepository.save(apostador);
        return apostador;
    }

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        return apostadorRepository.findByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("Apostador no encontrado"));

    }
}
