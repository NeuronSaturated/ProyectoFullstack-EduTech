package com.edutech.evaluacionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.evaluacionservice.model.Evaluacion;
import com.edutech.evaluacionservice.model.EvaluacionConEstudianteDTO;
import com.edutech.evaluacionservice.model.EvaluacionDTO;
import com.edutech.evaluacionservice.service.EvaluacionService;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public List<Evaluacion> listar() {
        return evaluacionService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDTO> obtenerEvaluacionPorId(@PathVariable Long id) {
        return evaluacionService.obtenerEvaluacionPorId(id);
    }

    @GetMapping("/detalladas")
    public List<EvaluacionConEstudianteDTO> listarConDetalles() {
        return evaluacionService.listarConDetalles();
    }


    @PostMapping
    public Evaluacion crear(@RequestBody Evaluacion evaluacion) {
        return evaluacionService.crear(evaluacion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        evaluacionService.eliminar(id);
    }

    @DeleteMapping("/clear")
    public void eliminarTodos() {
        evaluacionService.eliminarTodos();
    }

}
