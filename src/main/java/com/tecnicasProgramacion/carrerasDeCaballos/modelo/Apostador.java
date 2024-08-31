package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String dni;
    private String clave;
    @Getter
    @Setter
    private float montoAcumulado;
    private boolean esAdmin;

    public Apostador(String dni, String clave, boolean esAdmin) {
        this.dni = dni;
        this.clave = clave;
        this.esAdmin = esAdmin;
        this.montoAcumulado = 0;
    }

    public Apostador() {

    }

    public void apostar(Carrera carrera, Caballo caballo) {

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
