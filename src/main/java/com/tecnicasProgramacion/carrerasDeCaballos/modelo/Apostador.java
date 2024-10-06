package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.apuesta.Ganador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception.ApuestaInexistenteException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
public class Apostador implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(unique = true)
    private String dni;
    private String clave;
    @Getter
    @Setter
    private float montoAcumulado;
    private String nombre;
    private boolean esAdmin;

    public Apostador(String dni, String clave, String nombre, boolean esAdmin) {
        this.dni = dni;
        this.clave = clave;
        this.nombre = nombre;
        this.esAdmin = esAdmin;
        this.montoAcumulado = 0;
    }

    public Apostador() {

    }

    public Apuesta apostar(Carrera carrera, Caballo caballo, float monto, String tipo ) {
        Apuesta apuesta = null;
        switch (tipo) {
            case "Ganador":
                apuesta = new Ganador(monto, this, caballo);
                break;
            case "Segundo":
                break;
            default:
                throw new ApuestaInexistenteException();
        }
        carrera.getApuestas().add(apuesta);
        return apuesta;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.clave;
    }

    @Override
    public String getUsername() {
        return Integer.valueOf(this.dni).toString();
    }
}
