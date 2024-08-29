package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

public abstract class Apuesta {

    private float monto;
    private Apostador apostador;

    public Apuesta(float monto, Apostador apostador) {
        this.monto = monto;
        this.apostador = apostador;
    }

    public abstract float montoGanado();

    public void sumarPremio(){
        apostador.setMontoAcumulado(apostador.getMontoAcumulado() + this.montoGanado());
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Apostador getApostador() {
        return apostador;
    }

    public void setApostador(Apostador apostador) {
        this.apostador = apostador;
    }
}
