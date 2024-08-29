package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

public class Apostador {

    private int dni;
    private String clave;
    private float montoAcumulado;
    private boolean esAdmin;

    public Apostador(int dni, String clave, boolean esAdmin) {
        this.dni = dni;
        this.clave = clave;
        this.esAdmin = esAdmin;
        this.montoAcumulado = 0;
    }

    public void apostar(Carrera carrera, Caballo caballo) {

    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public float getMontoAcumulado() {
        return montoAcumulado;
    }

    public void setMontoAcumulado(float montoAcumulado) {
        this.montoAcumulado = montoAcumulado;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }
}
