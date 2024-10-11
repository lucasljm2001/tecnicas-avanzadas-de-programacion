package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

import com.tecnicasProgramacion.carrerasDeCaballos.CarrerasDeCaballosApplication;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.apuesta.Ganador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.apuesta.Segundo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SegundoTest {

    private Segundo segundo;
    private Carrera carrera;
    private Caballo caballo;
    private Apostador apostador;

    @BeforeEach
    void setUp() {
        apostador = mock(Apostador.class);
        caballo = mock(Caballo.class);
        carrera = mock(Carrera.class);
        segundo = new Segundo(100.0f, apostador, caballo, carrera);
    }

    @Test
    void siElCaballoApostadoSalePrimeroSeGanaLaMitad() {
        when(carrera.getGanador()).thenReturn(caballo);
        when(carrera.getSegundo()).thenReturn(mock(Caballo.class));
        when(caballo.getNombre()).thenReturn("Caballo1");
        when(segundo.getCaballo().getNombre()).thenReturn("Caballo1");

        float result = segundo.montoGanado(carrera);

        assertEquals(50.0f, result);
    }

    @Test
    void siElCaballoApostadoSaleSegundoSeGanaLaMitad() {
        when(carrera.getGanador()).thenReturn(mock(Caballo.class));
        when(carrera.getSegundo()).thenReturn(caballo);
        when(caballo.getNombre()).thenReturn("Caballo1");
        when(segundo.getCaballo().getNombre()).thenReturn("Caballo1");

        float result = segundo.montoGanado(carrera);

        assertEquals(50.0f, result);
    }

    @Test
    void siElCaballoApostadoNoTerminaEnLosDosPrimerosPuestosNoSeGanaNada() {
        when(carrera.getGanador()).thenReturn(mock(Caballo.class));
        when(carrera.getSegundo()).thenReturn(mock(Caballo.class));
        when(caballo.getNombre()).thenReturn("Caballo1");
        when(segundo.getCaballo().getNombre()).thenReturn("Caballo2");

        float result = segundo.montoGanado(carrera);

        assertEquals(0.0f, result);
    }

    @Test
    void siLaCarreraNoSeDefinioNoSeGanaNada() {
        when(carrera.getGanador()).thenReturn(null);
        when(carrera.getSegundo()).thenReturn(null);

        float result = segundo.montoGanado(carrera);

        assertEquals(0.0f, result);
    }

}
