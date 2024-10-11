package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

import com.tecnicasProgramacion.carrerasDeCaballos.CarrerasDeCaballosApplication;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.apuesta.Ganador;
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
public class GanadorTest {
    // PREGUNTAR, ME CREA UNA CONFIGURACION POR CADA ARCHIVO, NO SE COMO HACER PARA REUTILIZAR LA MISMA

    private Ganador ganador;
    private Carrera carrera;
    private Caballo caballo;
    private Apostador apostador;

    @BeforeEach
    void setUp() {
        apostador = mock(Apostador.class);
        caballo = mock(Caballo.class);
        carrera = mock(Carrera.class);
        ganador = new Ganador(100.0f, apostador, caballo, carrera);
    }

    @Test
    void elMontoGanadoPorUnaApuestaGanadorEsElTotal() {
        when(carrera.getGanador()).thenReturn(caballo);
        when(caballo.getNombre()).thenReturn("Caballo1");
        when(ganador.getCaballo().getNombre()).thenReturn("Caballo1");

        float result = ganador.montoGanado(carrera);

        assertEquals(100.0f, result);
    }

    @Test
    void elMontoGanadoEsCeroCuandoElCaballoApostadoNoGano() {
        Caballo otroCaballo = mock(Caballo.class);
        when(carrera.getGanador()).thenReturn(otroCaballo);
        when(otroCaballo.getNombre()).thenReturn("Caballo2");
        when(ganador.getCaballo().getNombre()).thenReturn("Caballo1");

        float result = ganador.montoGanado(carrera);

        assertEquals(0.0f, result);
    }

    @Test
    void noHayGananciaSiLaCarreraNoSeJugo() {
        when(carrera.getGanador()).thenReturn(null);

        float result = ganador.montoGanado(carrera);

        assertEquals(0.0f, result);
    }

}
