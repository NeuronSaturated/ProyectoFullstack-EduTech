package com.edutech.evaluacionservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.edutech.evaluacionservice.model.EstudianteDTO;
import com.edutech.evaluacionservice.model.Evaluacion;
import com.edutech.evaluacionservice.model.EvaluacionConEstudianteDTO;
import com.edutech.evaluacionservice.model.EvaluacionDTO;
import com.edutech.evaluacionservice.repository.EvaluacionRepository;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDTO> obtenerEvaluacionPorId(@PathVariable Long id) {
        Optional<Evaluacion> evaluacionOpt = evaluacionRepository.findById(id);
        if (evaluacionOpt.isPresent()) {
            Evaluacion evaluacion = evaluacionOpt.get();
            EvaluacionDTO dto = new EvaluacionDTO();
            dto.setNombreEvaluacion(evaluacion.getNombreEvaluacion());
            dto.setPuntaje(evaluacion.getPuntaje());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }


    public List<Evaluacion> listar() {
        return evaluacionRepository.findAll();
    }

    public List<EvaluacionConEstudianteDTO> listarConDetalles() {
        List<Evaluacion> evaluaciones = evaluacionRepository.findAll();
        List<EvaluacionConEstudianteDTO> resultado = new ArrayList<>();

        for (Evaluacion evaluacion : evaluaciones) {
            EvaluacionConEstudianteDTO dto = new EvaluacionConEstudianteDTO();
            dto.setId(evaluacion.getId());
            dto.setNombreEvaluacion(evaluacion.getNombreEvaluacion());
            dto.setPuntaje(evaluacion.getPuntaje());

            // Obtener estudiante por REST
            try {
                String urlEstudiante = "http://localhost:8081/api/estudiantes/" + evaluacion.getIdEstudiante();
                ResponseEntity<EstudianteDTO> response = restTemplate.getForEntity(urlEstudiante, EstudianteDTO.class);
                dto.setEstudiante(response.getBody());
            } catch (Exception e) {
                System.out.println("Error al obtener estudiante: " + e.getMessage());
            }

            resultado.add(dto);
        }

        return resultado;
    }

    public Evaluacion crear(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public void eliminar(Long id) {
        evaluacionRepository.deleteById(id);
    }

    public void eliminarTodos() {
        evaluacionRepository.deleteAll();
    }
}