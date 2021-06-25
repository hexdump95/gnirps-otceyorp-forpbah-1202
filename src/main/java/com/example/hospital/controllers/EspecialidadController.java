package com.example.hospital.controllers;

import com.example.hospital.entities.Especialidad;
import com.example.hospital.repositories.EspecialidadRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "ABM Especialidad")
@RequestMapping(path = "/especialidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class EspecialidadController {
    private final EspecialidadRepository especialidadRepository;

    public EspecialidadController(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @Operation
    @GetMapping
    public List<Especialidad> findAllEspecialidades() {
        return especialidadRepository.findAll();
    }

    @Operation
    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> findOneEspecialidad(@PathVariable Long id) {
        return especialidadRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @PostMapping
    public ResponseEntity<Especialidad> saveEspecialidad(@RequestBody Especialidad especialidad) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(especialidadRepository.save(especialidad));
    }

    @Operation
    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> putEspecialidad(@PathVariable Long id, @RequestBody Especialidad especialidad){
        return especialidadRepository.findById(id)
                .map(e -> especialidadRepository.save(especialidad))
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEspecialidad(@PathVariable Long id) {
        return especialidadRepository.findById(id)
                .map(e -> {
                    especialidadRepository.delete(e);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(); // TODO
    }
}
