package com.edutech.evaluacionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.evaluacionservice.model.Evaluacion;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
}