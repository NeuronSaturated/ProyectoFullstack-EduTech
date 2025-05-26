package com.edutech.cursoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.cursoservice.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
