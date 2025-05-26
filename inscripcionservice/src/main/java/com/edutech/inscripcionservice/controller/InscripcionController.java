package com.edutech.inscripcionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.inscripcionservice.model.Inscripcion;
import com.edutech.inscripcionservice.model.InscripcionConDetallesDTO;
import com.edutech.inscripcionservice.model.InscripcionConEstudianteDTO;
import com.edutech.inscripcionservice.model.InscripcionDTO;
import com.edutech.inscripcionservice.service.InscripcionService;


@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    // GET: /api/inscripciones
    @GetMapping
    public List<InscripcionConEstudianteDTO> listar() {
        return inscripcionService.listarConEstudiante();
    }

    // GET: /api/inscripciones/detalladas
    @GetMapping("/detalladas")
    public List<InscripcionConDetallesDTO> listarConDetalles() {
        return inscripcionService.listarConDetalles();
    }

    @GetMapping("/buscar")
    public ResponseEntity<InscripcionDTO> buscarInscripcionValida(
            @RequestParam Long estudianteId,
            @RequestParam Long cursoId
    ) {
        return inscripcionService.buscarInscripcionValida(estudianteId, cursoId);
    }



    @PostMapping
    public Inscripcion crear(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.crear(inscripcion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
    }

    @DeleteMapping("/clear")
    public void eliminarTodos() {
        inscripcionService.eliminarTodos();
    }
}
