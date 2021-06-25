package com.example.hospital.controllers;

import com.example.hospital.entities.Localidad;
import com.example.hospital.repositories.LocalidadRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "ABM Localidad")
@RequestMapping(path = "/localidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocalidadController {
    private final LocalidadRepository localidadRepository;

    public LocalidadController(LocalidadRepository localidadRepository) {
        this.localidadRepository = localidadRepository;
    }

    @Operation
    @GetMapping
    public List<Localidad> findAllLocalidades() {
        return localidadRepository.findAll();
    }

    @Operation
    @GetMapping("/{id}")
    public ResponseEntity<Localidad> findOneLocalidad(@PathVariable Long id) {
        return localidadRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @PostMapping
    public ResponseEntity<Localidad> saveLocalidad(@RequestBody Localidad localidad) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(localidadRepository.save(localidad));
    }

    @Operation
    @PutMapping("/{id}")
    public ResponseEntity<Localidad> putLocalidad(@PathVariable Long id, @RequestBody Localidad localidad){
        return localidadRepository.findById(id)
                .map(l -> localidadRepository.save(localidad))
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocalidad(@PathVariable Long id) {
        return localidadRepository.findById(id)
                .map(l -> {
                    localidadRepository.delete(l);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(); // TODO
    }
}
