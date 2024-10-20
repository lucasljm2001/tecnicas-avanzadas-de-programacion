package com.tecnicasProgramacion.carrerasDeCaballos.controller;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.ApostadorDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.ApuestaDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.AuthResponseDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.ErrorDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.helper.MockMVCApostadorController;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.service.ApostadorService;
import com.tecnicasProgramacion.carrerasDeCaballos.service.ApuestaService;
import com.tecnicasProgramacion.carrerasDeCaballos.utils.DataSpringService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.xml.crypto.Data;
import java.util.ArrayList;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApostadorControllerTest {

    @Autowired
    private MockMVCApostadorController mockMVCApostadorController;

    @Autowired
    private DataSpringService dataSpringService;


    private String accessToken;

    @Autowired
    private ApuestaService apuestaService;

    @Autowired
    private ApostadorService apostadorService;



    @BeforeEach
    public void setUp() throws Throwable {
        dataSpringService.cleanUp();
        dataSpringService.loadData();
        Apostador principal = apostadorService.recuperarApostador("567").get();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

    }


    @Test
    public void loginCorrecto() throws Throwable {
        AuthResponseDTO accessToken = mockMVCApostadorController.login("567", "pepe", "pepe", HttpStatus.OK);
        Assertions.assertNotNull(accessToken.getAccessToken());
    }

    @Test
    public void loginConClaveIncorrecta() throws Throwable {
        Assertions.assertThrows(UnrecognizedPropertyException.class, () -> {
            mockMVCApostadorController.login("567", "pep", "asd", HttpStatus.UNAUTHORIZED);
        });
    }

    @Test
    public void loginConDniIncorrecto() throws Throwable {
        Assertions.assertThrows(UnrecognizedPropertyException.class, () -> {
            mockMVCApostadorController.login("999", "pepe", "pepe", HttpStatus.UNAUTHORIZED);
        });
    }

    @Test
    public void loginConDniNulo() throws Throwable {
        Assertions.assertThrows(MismatchedInputException.class, () -> {
            mockMVCApostadorController.login(null, "pepe", "pepe", HttpStatus.BAD_REQUEST);
        });
    }

    @Test
    public void loginConDniVacio() throws Throwable {
        Assertions.assertThrows(MismatchedInputException.class, () -> {
            mockMVCApostadorController.login("", "pepe", "pepe", HttpStatus.BAD_REQUEST);
        });
    }

    @Test
    public void loginConNombreIncorrecto() throws Throwable {
        Assertions.assertThrows(UnrecognizedPropertyException.class, () -> {
            mockMVCApostadorController.login("567", "incorrecto", "pepe", HttpStatus.UNAUTHORIZED);
        });
    }

    @Test
    public void loginConNombreNulo() throws Throwable {
        Assertions.assertThrows(MismatchedInputException.class, () -> {
            mockMVCApostadorController.login("567", null, "pepe", HttpStatus.BAD_REQUEST);
        });
    }

    @Test
    public void loginConNombreVacio() throws Throwable {
        Assertions.assertThrows(MismatchedInputException.class, () -> {
            mockMVCApostadorController.login("567", "", "pepe", HttpStatus.BAD_REQUEST);
        });
    }

    @Test
    public void loginConClaveNula() throws Throwable {
        Assertions.assertThrows(MismatchedInputException.class, () -> {
            mockMVCApostadorController.login("567", "pepe", null, HttpStatus.BAD_REQUEST);
        });
    }

    @Test
    public void loginConClaveVacia() throws Throwable {
        Assertions.assertThrows(MismatchedInputException.class, () -> {
            mockMVCApostadorController.login("567", "pepe", "", HttpStatus.BAD_REQUEST);
        });
    }

    @Test
    public void pepeApuestaCorrectamenteYVeLaApuestaEnSuPerfil() throws Throwable {
        ApostadorDTO perfilViejo = mockMVCApostadorController.perfil(HttpStatus.OK);
        Assertions.assertEquals(0, perfilViejo.getApuestas().size());
        ApuestaDTO apuestaDTO = mockMVCApostadorController.apostar("carrera1", "caballo1", "Ganador", 100, HttpStatus.OK);
        ApostadorDTO perfil = mockMVCApostadorController.perfil(HttpStatus.OK);
        Assertions.assertEquals(1, perfil.getApuestas().size());
        Assertions.assertEquals(apuestaDTO.getCaballo(), perfil.getApuestas().get(0).getCaballo());
        Assertions.assertEquals(apuestaDTO.getCarrera(), perfil.getApuestas().get(0).getCarrera());
        Assertions.assertEquals(apuestaDTO.getMonto(), perfil.getApuestas().get(0).getMonto());
        Assertions.assertEquals(apuestaDTO.getTipo(), perfil.getApuestas().get(0).getTipo());
    }

    @Test
    public void pepeApuestaDosVecesEnLaMismaCarreraYVeError() throws Throwable {
        mockMVCApostadorController.apostar("carrera1", "caballo1", "Ganador", 100, HttpStatus.OK);
        Assertions.assertThrows(ServletException.class, () -> {
            mockMVCApostadorController.apostar("carrera1", "caballo1", "Ganador", 100, HttpStatus.BAD_REQUEST);
        });
    }

    @Test
    public void pepeApuestaEnCarreraYaIniciadaYVeError() throws Throwable {
        Assertions.assertThrows(ServletException.class, () -> {
            mockMVCApostadorController.apostar("iniciada", "caballo1", "Ganador", 100, HttpStatus.BAD_REQUEST);
        });
    }





}
