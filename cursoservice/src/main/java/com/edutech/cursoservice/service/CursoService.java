package com.edutech.cursoservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.cursoservice.model.Curso;
import com.edutech.cursoservice.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }


    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    public void eliminarTodos() {
        cursoRepository.deleteAll();
    }
}