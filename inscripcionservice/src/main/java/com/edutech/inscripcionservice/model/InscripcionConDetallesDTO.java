package com.edutech.inscripcionservice.model;

public class InscripcionConDetallesDTO {
    private Long id;
    private String fechaInscripcion;
    private Long idCurso;
    private Long idEstudiante;
    private CursoDTO curso;
    private EstudianteDTO estudiante;

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }
    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Long getIdCurso() {
        return idCurso;
    }
    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }
    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public CursoDTO getCurso() {
        return curso;
    }
    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }
    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }
}
