package com.edutech.inscripcionservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.inscripcionservice.model.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    Optional<Inscripcion> findByIdEstudianteAndIdCurso(Long idEstudiante, Long idCurso);
}