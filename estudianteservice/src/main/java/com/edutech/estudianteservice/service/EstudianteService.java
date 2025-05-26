package com.edutech.estudianteservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.estudianteservice.model.Estudiante;
import com.edutech.estudianteservice.repository.EstudianteRepository;


@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    public Estudiante crear(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public void eliminar(Long id) {
        estudianteRepository.deleteById(id);
    }

    public Optional<Estudiante> buscarPorId(Long id) {
        return estudianteRepository.findById(id);
    }
    public void eliminarTodos() {
        estudianteRepository.deleteAll();
    }
}
