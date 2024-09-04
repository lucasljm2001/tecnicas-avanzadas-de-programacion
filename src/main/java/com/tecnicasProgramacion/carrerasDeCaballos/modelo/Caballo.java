package com.tecnicasProgramacion.carrerasDeCaballos.modelo;

public class Caballo {

    private String nombre;
    private float peso;
    private float altura;
    private int edad;

    public void inscribir(Carrera carrera) {
        carrera.getCompetidores().add(this);
    }


    public float velocidad(){
        return 40 / ((peso+altura+edad)*0.25f);
    }

    public float velocidadConObstaculos(){
        return velocidad()/1.6f;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
