package com.tecnicasProgramacion.carrerasDeCaballos.service;

import com.tecnicasProgramacion.carrerasDeCaballos.CarrerasDeCaballosApplication;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apuesta;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.CarreraNormal;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.TipoDeCarrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.*;
import com.tecnicasProgramacion.carrerasDeCaballos.repository.ApostadorRepository;
import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.ApostadorServiceImpl;
import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.CarreraServiceImpl;
import com.tecnicasProgramacion.carrerasDeCaballos.utils.DataSpringService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApostadorServiceTest {

    @Autowired
    ApostadorService apostadorService;
    @Autowired
    private CarreraService carreraService;
    @Autowired
    private CaballoService caballoService;
    @Autowired
    private ApuestaService apuestaService;

    @Autowired
    private DataSpringService dataSpringService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {

        dataSpringService.cleanUp();

        apostadorService.crearApostador("1234", passwordEncoder.encode("admin"), "admin", true);
        apostadorService.crearApostador("567", passwordEncoder.encode("pepe"), "pepe", false);
        Caballo caballo = caballoService.crearCaballo("veloz", 50.0f, 10f, 5);
        Caballo caballo1 = caballoService.crearCaballo("caballo1", 40f, 22f, 10);
        Caballo caballo2 = caballoService.crearCaballo("caballo2", 20f, 33f, 23);
        Caballo caballo3 = caballoService.crearCaballo("caballo3", 44f, 11f, 33);
        Carrera carrera = carreraService.crearCarrera(LocalDateTime.of(2024, 12, 10, 10, 10), 100, "carrera1", TipoDeCarrera.CARRERA_NORMAL);
        carreraService.agregarCaballo(carrera, caballo);
        carreraService.agregarCaballo(carrera, caballo1);
        carreraService.agregarCaballo(carrera, caballo2);
        Carrera carrera2 = carreraService.crearCarrera(LocalDateTime.of(2024, 12, 10, 10, 10), 100, "carrera2", TipoDeCarrera.CARRERA_NORMAL);
        Carrera iniciada = carreraService.crearCarrera(LocalDateTime.now().plusNanos(100000), 100, "iniciada", TipoDeCarrera.CARRERA_NORMAL);


        Authentication authentication = new TestingAuthenticationToken("567", "pepe", "ROLE_USER");

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }


    @Test
    void creacionCorrectaDeUnaApuesta() {

        Apuesta result = apostadorService.apostar("Ganador", 100.0f, "caballo1", "carrera1");

        assertNotNull(result);
        assertEquals("pepe", result.getApostador().getNombre());
        assertEquals("caballo1", result.getCaballo().getNombre());
        assertEquals("carrera1", result.getCarrera().getNombre());
        assertEquals(100.0f, result.getMonto());
        assertEquals("Ganador", result.getClass().getSimpleName());
    }

    @Test
    void noSePuedeApostarSinDeclararElTipoDeApuesta() {

        assertThrows(ElTipoDeApuestaNoPuedeSerVacioException.class, () -> {
            apostadorService.apostar("", 100.0f, "Caballo1", "Carrera1");
        });
    }

    @Test
    void noSePuedeApostarUnMontoNegativo() {

        assertThrows(ElMontoDeLaApuestaNoPuedeSerNegativoException.class, () -> {
            apostadorService.apostar("tipo1", -100.0f, "caballo1", "carrera1");
        });
    }

    @Test
    void noSePuedeApostarEnUnaCarreraYaIniciada() {

        assertThrows(LaCarreraYaInicioException.class, () -> {
            apostadorService.apostar("Segundo", 100.0f, "caballo1", "iniciada");
        });
    }

    @Test
    void noSePuedeApostarPorUnCaballoQueNoParticipaEnLaCarrera() {

        assertThrows(ElCaballoNoParticipaEnLaCarreraException.class, () -> {
            apostadorService.apostar("Ganador", 100.0f, "caballo3", "carrera1");
        });
    }

    @Test
    void noSePuedeApostarDosVecesEnLaMismaCarrera() {
        Apuesta apuesta = apostadorService.apostar("Ganador", 100.0f, "caballo1", "carrera1");


        assertThrows(YaExisteLaApuestaException.class, () -> {
            apostadorService.apostar("Ganador", 100.0f, "caballo1", "carrera1");
        });
    }

}
