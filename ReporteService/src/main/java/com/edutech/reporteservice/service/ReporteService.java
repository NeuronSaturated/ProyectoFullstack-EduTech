package com.edutech.reporteservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.edutech.reporteservice.model.CursoDTO;
import com.edutech.reporteservice.model.EstudianteDTO;
import com.edutech.reporteservice.model.InscripcionDTO;
import com.edutech.reporteservice.model.NotificacionDTO;
import com.edutech.reporteservice.model.ReporteDTO;




@Service
public class ReporteService {

    @Autowired
    private RestTemplate restTemplate;

    public List<ReporteDTO> obtenerReportes() {
        List<ReporteDTO> lista = new ArrayList<>();

        // IDs fijos por ahora
        Long estudianteId = 8L;
        Long cursoId = 5L;


        // Obtener datos REST
        EstudianteDTO estudiante = restTemplate.getForObject("http://localhost:8081/api/estudiantes/" + estudianteId, EstudianteDTO.class);
        CursoDTO curso = restTemplate.getForObject("http://localhost:8080/api/cursos/" + cursoId, CursoDTO.class);
        NotificacionDTO notificacion = restTemplate.getForObject("http://localhost:8084/api/notificaciones/ultima", NotificacionDTO.class);
        InscripcionDTO inscripcion = restTemplate.getForObject("http://localhost:8083/api/inscripciones/buscar?estudianteId=" + estudianteId + "&cursoId=" + cursoId,InscripcionDTO.class);


        // Llenar DTO
        ReporteDTO dto = new ReporteDTO();

        if (inscripcion != null) {
            dto.setFechaInscripcion(inscripcion.getFechaInscripcion());
        } else {
            dto.setFechaInscripcion("Sin inscripción");
        }

        if (estudiante != null) {
            dto.setNombreEstudiante(estudiante.getNombre());
            dto.setCorreoEstudiante(estudiante.getCorreo());
        } else {
            dto.setNombreEstudiante("Estudiante no encontrado");
            dto.setCorreoEstudiante("-");
        }

        if (curso != null) {
            dto.setCurso(curso.getNombre());
            dto.setDescripcionCurso(curso.getDescripcion());
        } else {
            dto.setCurso("Curso no encontrado");
            dto.setDescripcionCurso("-");
        }

        if (notificacion != null) {
            dto.setFechaNotificacion(notificacion.getFechaEnvio());
        } else {
            dto.setFechaNotificacion("Sin fecha");
        }

        lista.add(dto);
        return lista;
    }
}
