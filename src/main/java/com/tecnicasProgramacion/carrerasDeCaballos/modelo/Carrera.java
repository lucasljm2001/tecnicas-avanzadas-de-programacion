package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.TipoDeCarrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.NoHaySuficientesCaballosException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.context.annotation.Primary;

import javax.validation.Constraint;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Carrera {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Column(unique = true)
    private String nombre;

    @Getter
    @Setter
    private LocalDateTime fechaYHora;

    @Getter
    @Setter
    @ManyToOne
    private Caballo ganador;

    @Getter
    @Setter
    @ManyToOne
    private Caballo segundo;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Caballo> competidores;

    @Getter
    @Setter
    private int distancia;
    @Getter
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Apuesta> apuestas;

    public Carrera(LocalDateTime fechaYHora, int distancia, Set<Caballo> competidores, Set<Apuesta> apuestas, String nombre) {
        this.fechaYHora = fechaYHora;
        this.distancia = distancia;
        this.competidores = competidores;
        this.apuestas = apuestas;
        this.nombre = nombre;
    }

    public Carrera(LocalDateTime fechaYHora, int distancia, String nombre) {
        this.fechaYHora = fechaYHora;
        this.distancia = distancia;
        this.competidores = new HashSet<Caballo>();
        this.apuestas = new HashSet<Apuesta>();
        this.nombre = nombre;
    }

    public Carrera() {

    }

    public void agregarCompetidor(Caballo caballo){
        competidores.add(caballo);
    }

    public abstract double totalDeVelocidad();

    public abstract double velocidadDelCaballo(Caballo caballo);

    public void determinarPosicones(){

        if (competidores.size() < 2) throw new NoHaySuficientesCaballosException();

        List<Caballo> caballosList = new ArrayList<>(competidores);
        List<Caballo> posicionesAsignadas = new ArrayList<>();

        Random random = new Random();

        int posicion = 1;
        while (!caballosList.isEmpty()) {
            double totalVelocidad = this.totalDeVelocidad();

            double randomValue = random.nextDouble() * totalVelocidad;

            double acumulado = 0;
            Caballo caballoElegido = null;
            for (Caballo caballo : caballosList) {
                acumulado += this.velocidadDelCaballo(caballo);
                if (acumulado >= randomValue) {
                    caballoElegido = caballo;
                    break;
                }
            }

            if (caballoElegido != null) {
                posicionesAsignadas.add(caballoElegido);
                caballosList.remove(caballoElegido);
            }



        }
        this.setGanador(posicionesAsignadas.get(0));
        this.setSegundo(posicionesAsignadas.get(1));

        this.setFechaYHora(LocalDateTime.now());

        this.apuestas.forEach(this::premio);
    }



    private long apuestasAlMismoCaballo(Caballo caballo){
        return apuestas.stream().filter(apuesta1 -> apuesta1.getCaballo().getNombre().equals(caballo.getNombre())).count();
    }

    public float porcentajeDePago(Caballo caballo){
        long apuestasAlMismoCaballo = apuestasAlMismoCaballo(caballo);
        return  1 -  (0.05f * apuestasAlMismoCaballo);
    }

    public void premio(Apuesta apuesta){

        Apostador apostador = apuesta.getApostador();

        float montoASumar = apuesta.montoGanado(this) - porcentajeDePago(apuesta.getCaballo());

        apostador.setMontoAcumulado(apostador.getMontoAcumulado() + montoASumar);
    }



    public void setId(int id) {
        this.id = id;
    }

    public abstract TipoDeCarrera getTipoCarrera();


}
