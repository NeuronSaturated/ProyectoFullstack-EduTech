package com.edutech.evaluacionservice.model;


public class EvaluacionConEstudianteDTO {
    private Long id;
    private String nombreEvaluacion;
    private int puntaje;
    private EstudianteDTO estudiante;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreEvaluacion() { return nombreEvaluacion; }
    public void setNombreEvaluacion(String nombreEvaluacion) { this.nombreEvaluacion = nombreEvaluacion; }

    public int getPuntaje() { return puntaje; }
    public void setPuntaje(int puntaje) { this.puntaje = puntaje; }

    public EstudianteDTO getEstudiante() { return estudiante; }
    public void setEstudiante(EstudianteDTO estudiante) { this.estudiante = estudiante; }
}
