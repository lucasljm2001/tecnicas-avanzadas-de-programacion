package com.tecnicasProgramacion.carrerasDeCaballos.modelo.exception;

public class LaCarreraYaInicioException extends RuntimeException{

    @Override
    public String getMessage() {
        return "No se puede apostar a una carrera ya inicida";
    }
}
