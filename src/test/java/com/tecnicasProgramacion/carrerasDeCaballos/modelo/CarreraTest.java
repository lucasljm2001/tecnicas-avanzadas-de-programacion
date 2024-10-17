package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.apuesta.Ganador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.CarreraNormal;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.NoHaySuficientesCaballosException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class CarreraTest {

    private Carrera carrera;
    private Caballo caballo1;
    private Caballo caballo2;
    private Apuesta apuestaMock;
    private Apostador apostadorMock;

    @BeforeEach
    public void setUp() {
        caballo1 = Mockito.mock(Caballo.class);
        caballo2 = Mockito.mock(Caballo.class);
        apuestaMock = Mockito.mock(Apuesta.class);
        apostadorMock = Mockito.mock(Apostador.class);

        Mockito.when(caballo1.getNombre()).thenReturn("caballo1");
        Mockito.when(caballo2.getNombre()).thenReturn("caballo2");
        Mockito.when(caballo1.getAltura()).thenReturn(10.0f);
        Mockito.when(caballo2.getAltura()).thenReturn(12.0F);
        Mockito.when(apuestaMock.getCaballo()).thenReturn(caballo1);
        Mockito.when(apuestaMock.getApostador()).thenReturn(apostadorMock);
        Mockito.when(apuestaMock.montoGanado(Mockito.any(Carrera.class))).thenReturn(100.0f);

        Set<Caballo> competidores = new HashSet<>();
        competidores.add(caballo1);
        competidores.add(caballo2);


        carrera = new CarreraNormal(LocalDateTime.now().plusDays(1), 1000, "carreraTest") {
            @Override
            public double totalDeVelocidad() {
                return competidores.stream().mapToDouble(Caballo::getAltura).sum();
            }

            @Override
            public double velocidadDelCaballo(Caballo caballo) {
                return caballo.getAltura();
            }
        };
        competidores.forEach(carrera::agregarCompetidor);
        carrera.getApuestas().add(apuestaMock);
    }

    @Test
    public void carreraDeterminaPosicionesCorrectamente() {
        carrera.determinarPosicones();
        Assertions.assertNotNull(carrera.getGanador());
        Assertions.assertNotNull(carrera.getSegundo());
    }

    @Test
    public void carreraNoDeterminaPosicionesConMenosDeDosCaballos() {
        carrera.getCompetidores().remove(caballo2);
        Assertions.assertThrows(NoHaySuficientesCaballosException.class, carrera::determinarPosicones);
    }

    @Test
    public void carreraCalculaPorcentajeDePagoCorrectamente() {
        Assertions.assertEquals(0.95f, carrera.porcentajeDePago(caballo1));
    }

    @Test
    public void carreraCalculaPorcentajeDePagoConMultiplesApuestas() {
        carrera.getApuestas().add(new Ganador(100, new Apostador("123", "absc","apostador1", false), caballo1, carrera));
        carrera.getApuestas().add(new Ganador(200, new Apostador("456", "absc","apostador2", false), caballo1, carrera));
        Assertions.assertEquals(0.85f, carrera.porcentajeDePago(caballo1));
    }

    @Test
    public void carreraPremiaApostadorCorrectamente() {
        carrera.determinarPosicones();
        float montoEsperado = apuestaMock.montoGanado(carrera) - carrera.porcentajeDePago(caballo1);
        Mockito.verify(apostadorMock).setMontoAcumulado(apostadorMock.getMontoAcumulado() + montoEsperado);
    }
}