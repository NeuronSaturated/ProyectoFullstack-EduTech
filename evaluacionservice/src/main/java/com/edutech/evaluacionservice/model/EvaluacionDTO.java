package com.edutech.evaluacionservice.model;

public class EvaluacionDTO {

    private String nombreEvaluacion;
    private int puntaje;

    // Getters y Setters
    public String getNombreEvaluacion() {
        return nombreEvaluacion;
    }

    public void setNombreEvaluacion(String nombreEvaluacion) {
        this.nombreEvaluacion = nombreEvaluacion;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}