package com.tecnicasProgramacion.carrerasDeCaballos.service;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.TipoDeCarrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.NoExisteLaCarreraException;
import com.tecnicasProgramacion.carrerasDeCaballos.utils.DataSpringService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarreraServiceTest {
    @Autowired
    CarreraService carreraService;

    @Autowired
    DataSpringService dataSpringService;

    @Autowired
    CaballoService caballoService;

    @BeforeEach
    public void setup() {
        dataSpringService.cleanUp();
        dataSpringService.loadData();
    }

    @Test
    public void seCreaUnaCarrera() {
        Assertions.assertThrows(NoExisteLaCarreraException.class, () -> carreraService.recuperarCarrera("carrera4"));
        carreraService.crearCarrera(LocalDateTime.now().plusDays(1), 1000, "carrera4", TipoDeCarrera.CARRERA_NORMAL);
        Assertions.assertEquals("carrera4", carreraService.recuperarCarrera("carrera4").get().getNombre());
    }

    @Test
    public void seAgregaUnCaballoAUnaCarreraNueva(){
        carreraService.crearCarrera(LocalDateTime.now().plusDays(1), 1000, "carrera4", TipoDeCarrera.CARRERA_NORMAL);
        Assertions.assertEquals(0, carreraService.recuperarCarrera("carrera4").get().getCompetidores().size());
        carreraService.agregarCaballo(carreraService.recuperarCarrera("carrera4").get(), caballoService.recuperarCaballo("caballo1").get());
        Assertions.assertEquals(1, carreraService.recuperarCarrera("carrera4").get().getCompetidores().size());
    }

    @Test
    public void seAgregaUnCaballoAUnaCarreraExistente(){
        Assertions.assertEquals(3, carreraService.recuperarCarrera("carrera1").get().getCompetidores().size());
        carreraService.agregarCaballo(carreraService.recuperarCarrera("carrera1").get(), caballoService.recuperarCaballo("caballo3").get());
        Assertions.assertEquals(4, carreraService.recuperarCarrera("carrera1").get().getCompetidores().size());

    }
}
