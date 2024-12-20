package com.tecnicasProgramacion.carrerasDeCaballos.controller;

import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraConCaballosDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraInformacionDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraTerminadaDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.helper.MockMVCCarreraController;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.TipoDeCarrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.CarreraYaJugadaException;
import com.tecnicasProgramacion.carrerasDeCaballos.service.ApostadorService;
import com.tecnicasProgramacion.carrerasDeCaballos.service.CarreraService;
import com.tecnicasProgramacion.carrerasDeCaballos.utils.DataSpringService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Thread.sleep;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarreraControllerTest {
    @Autowired
    private MockMVCCarreraController mockMVCCarreraController;

    @Autowired
    private DataSpringService dataSpringService;

    @Autowired
    private CarreraService carreraService;

    @Autowired
    private ApostadorService apostadorService;

    @BeforeEach
    public void setUp() throws Throwable {
        dataSpringService.cleanUp();
        dataSpringService.loadData();
        Apostador principal = apostadorService.recuperarApostador("1234").get();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void crearCarreraCorrectamente() throws Throwable {
        CarreraDTO carreraDTO = new CarreraDTO(LocalDateTime.now().plusDays(1), 1000, "carrera4", TipoDeCarrera.CARRERA_NORMAL);
        CarreraDTO createdCarrera = mockMVCCarreraController.crearCarrera(carreraDTO, HttpStatus.OK);
        Assertions.assertNotNull(createdCarrera);
    }

    @Test
    public void agregarCaballoCorrectamente() throws Throwable {
        Assertions.assertEquals(3, carreraService.recuperarCarrera("carrera1").get().getCompetidores().size());
        CarreraConCaballosDTO carreraConCaballosDTO = mockMVCCarreraController.agregarCaballo("carrera1", "caballo3", HttpStatus.OK);
        Assertions.assertEquals(4, carreraService.recuperarCarrera("carrera1").get().getCompetidores().size());
    }

    @Test
    public void iniciarCarreraCorrectamente() throws Throwable {
        sleep(1000);
        CarreraTerminadaDTO carreraTerminadaDTO = mockMVCCarreraController.iniciarCarrera("paraIniciar", HttpStatus.OK);
        Assertions.assertNotNull(carreraService.recuperarCarrera("paraIniciar").get().getGanador());
    }

    @Test
    public void noSePuedeAgregarUnCaballoAUnaCarreraIniciada() throws Throwable {
        sleep(1000);
        CarreraTerminadaDTO carreraTerminadaDTO = mockMVCCarreraController.iniciarCarrera("paraIniciar", HttpStatus.OK);
        Assertions.assertThrows(ServletException.class, () -> {
            mockMVCCarreraController.agregarCaballo("paraIniciar", "caballo3", HttpStatus.BAD_REQUEST);
        });
    }

    @Test
    public void lasCarrerasIniciadasNoApareceEnElListado() throws Throwable{
        List<CarreraInformacionDTO> carrerasAntes = mockMVCCarreraController.listarCarreras("0", "10", HttpStatus.OK);
        Assertions.assertEquals(4,  carrerasAntes.size());
        sleep(1000);
        List<CarreraInformacionDTO> carreras = mockMVCCarreraController.listarCarreras("0", "10", HttpStatus.OK);
        Assertions.assertEquals(2, carreras.size());
    }


}
