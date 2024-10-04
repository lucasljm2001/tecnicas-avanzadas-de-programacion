package com.tecnicasProgramacion.carrerasDeCaballos.controller;

import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraInformacionDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraController {


    @Autowired
    private CarreraService carreraService;

    @GetMapping("/listar/{page}/{size}")
    public List<CarreraInformacionDTO> obtenerCarrerasPaginadas(
            @PathVariable int page,
            @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size);
        return carreraService.obtenerCarrerasDisponibles(page, size).stream().map(CarreraInformacionDTO::new).toList();
    }



}
