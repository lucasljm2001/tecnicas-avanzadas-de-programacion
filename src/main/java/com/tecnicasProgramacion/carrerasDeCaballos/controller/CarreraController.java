package com.tecnicasProgramacion.carrerasDeCaballos.controller;

import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraConCaballosDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraInformacionDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraTerminadaDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.NoEsAdminException;
import com.tecnicasProgramacion.carrerasDeCaballos.service.ApostadorService;
import com.tecnicasProgramacion.carrerasDeCaballos.service.CaballoService;
import com.tecnicasProgramacion.carrerasDeCaballos.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraController {


    @Autowired
    private CarreraService carreraService;

    @Autowired
    private ApostadorService apostadorService;

    @Autowired
    private CaballoService caballoService;

    @GetMapping("/listar/{page}/{size}")
    public List<CarreraInformacionDTO> obtenerCarrerasPaginadas(
            @PathVariable int page,
            @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size);
        return carreraService.obtenerCarrerasDisponibles(page, size).stream().map(CarreraInformacionDTO::new).toList();
    }

    @PostMapping("/crear")
    public CarreraDTO crearCarrera(@RequestBody CarreraDTO carreraDTO) {
        Apostador apostador = apostadorService.recuperarApostador(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        if (!apostador.isEsAdmin()) throw new NoEsAdminException();
        Carrera carrera = carreraService.crearCarrera(carreraDTO.getFechaYHora(), carreraDTO.getDistancia(), carreraDTO.getNombre(), carreraDTO.getTipoCarrera());
        return new CarreraDTO(carrera);
    }

    @PutMapping("/agregarCaballo/{carrera}/{caballo}")
    public CarreraConCaballosDTO agregarCaballo(@PathVariable String carrera, @PathVariable String caballo) {
        Apostador apostador = apostadorService.recuperarApostador(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        if (!apostador.isEsAdmin()) throw new NoEsAdminException();
        Caballo caballoRecuperado = caballoService.recuperarCaballo(caballo).get();
        Carrera carreraRecuperada = carreraService.recuperarCarrera(carrera).get();
        Carrera carreraModificada = carreraService.agregarCaballo(carreraRecuperada, caballoRecuperado);
        return new CarreraConCaballosDTO(carreraModificada);
    }


    @PutMapping("/iniciar/{carrera}")
    public CarreraTerminadaDTO iniciarCarrera(@PathVariable String carrera){
        Apostador apostador = apostadorService.recuperarApostador(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        if (!apostador.isEsAdmin()) throw new NoEsAdminException();
        Carrera carreraRecuperada = carreraService.recuperarCarrera(carrera).get();
        Carrera carreraModificada = carreraService.iniciarCarrera(carreraRecuperada);
        return new CarreraTerminadaDTO(carreraModificada);
    }



}
