package com.edutech.inscripcionservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.edutech.inscripcionservice.model.CursoDTO;
import com.edutech.inscripcionservice.model.EstudianteDTO;
import com.edutech.inscripcionservice.model.Inscripcion;
import com.edutech.inscripcionservice.model.InscripcionConDetallesDTO;
import com.edutech.inscripcionservice.model.InscripcionConEstudianteDTO;
import com.edutech.inscripcionservice.model.InscripcionDTO;
import com.edutech.inscripcionservice.repository.InscripcionRepository;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

    public List<InscripcionConEstudianteDTO> listarConEstudiante() {
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();
        List<InscripcionConEstudianteDTO> resultado = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            String url = "http://localhost:8081/api/estudiantes/" + inscripcion.getIdEstudiante();

            try {
                ResponseEntity<EstudianteDTO> response = restTemplate.getForEntity(url, EstudianteDTO.class);
                EstudianteDTO estudiante = response.getBody();

                InscripcionConEstudianteDTO dto = new InscripcionConEstudianteDTO();
                dto.setId(inscripcion.getId());
                dto.setFechaInscripcion(inscripcion.getFechaInscripcion());
                dto.setIdCurso(inscripcion.getIdCurso());
                dto.setEstudiante(estudiante);

                resultado.add(dto);
            } catch (Exception e) {
                System.out.println("No se pudo obtener estudiante para inscripción ID " + inscripcion.getId() + ": " + e.getMessage());
            }
        }

        return resultado;
    }

    public Inscripcion crear(Inscripcion inscripcion) {
        // Validar que el estudiante existe (esto ya está hecho)
        String urlEstudiante = "http://localhost:8081/api/estudiantes/" + inscripcion.getIdEstudiante();
        String urlCurso = "http://localhost:8080/api/cursos/" + inscripcion.getIdCurso();

        try {
            // Consulta al microservicio CursoService
            ResponseEntity<CursoDTO> responseCurso = restTemplate.getForEntity(urlCurso, CursoDTO.class);
            CursoDTO curso = responseCurso.getBody();

            if (curso == null || curso.getNombre() == null) {
                throw new RuntimeException("El curso no existe o sus datos están incompletos.");
            }

            // (ya hecho) Consulta al microservicio EstudianteService
            ResponseEntity<EstudianteDTO> responseEstudiante = restTemplate.getForEntity(urlEstudiante, EstudianteDTO.class);
            EstudianteDTO estudiante = responseEstudiante.getBody();

            if (estudiante != null && estudiante.getNombre() != null) {
                return inscripcionRepository.save(inscripcion);
            } else {
                throw new RuntimeException("El estudiante no existe o sus datos están incompletos.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al validar inscripción: " + e.getMessage());
        }
    }

    public List<InscripcionConDetallesDTO> listarConDetalles() {
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();
        List<InscripcionConDetallesDTO> resultado = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            InscripcionConDetallesDTO dto = new InscripcionConDetallesDTO();
            dto.setId(inscripcion.getId());
            dto.setFechaInscripcion(inscripcion.getFechaInscripcion().toString());
            dto.setIdCurso(inscripcion.getIdCurso());
            dto.setIdEstudiante(inscripcion.getIdEstudiante());

            // Obtener estudiante por REST
            try {
                String urlEstudiante = "http://localhost:8081/api/estudiantes/" + inscripcion.getIdEstudiante();
                ResponseEntity<EstudianteDTO> responseEstudiante = restTemplate.getForEntity(urlEstudiante, EstudianteDTO.class);
                dto.setEstudiante(responseEstudiante.getBody());
            } catch (Exception e) {
                System.out.println("Error al obtener estudiante: " + e.getMessage());
            }

            // Obtener curso por REST
            try {
                String urlCurso = "http://localhost:8080/api/cursos/" + inscripcion.getIdCurso();
                ResponseEntity<CursoDTO> responseCurso = restTemplate.getForEntity(urlCurso, CursoDTO.class);
                dto.setCurso(responseCurso.getBody()); // ← ESTA LÍNEA FALTABA
            } catch (Exception e) {
                System.out.println("Error al obtener curso: " + e.getMessage());
            }

            resultado.add(dto);
        }


        return resultado;
    }

    @GetMapping("/buscar")
    public ResponseEntity<InscripcionDTO> buscarInscripcionValida(
            @RequestParam Long estudianteId,
            @RequestParam Long cursoId) {

        Optional<Inscripcion> inscripcion = inscripcionRepository.findByIdEstudianteAndIdCurso(estudianteId, cursoId);

        if (inscripcion.isPresent()) {
            InscripcionDTO dto = new InscripcionDTO();
            dto.setFechaInscripcion(inscripcion.get().getFechaInscripcion().toString());
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }



    public void eliminar(Long id) {
        inscripcionRepository.deleteById(id);
    }

    public void eliminarTodos() {
        inscripcionRepository.deleteAll();
    }
}
