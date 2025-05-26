package com.edutech.estudianteservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.estudianteservice.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
