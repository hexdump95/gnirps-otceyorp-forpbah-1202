package com.example.hospital.controllers;

import com.example.hospital.entities.Persona;
import com.example.hospital.repositories.PersonaRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/personas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonaController {
    private final PersonaRepository personaRepository;

    public PersonaController(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Operation
    @GetMapping
    public List<Persona> findAllPersonas() {
        return personaRepository.findAll();
    }

    @Operation
    @GetMapping("/{id}")
    public ResponseEntity<Persona> findOnePersona(@PathVariable Long id) {
        return personaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @PostMapping
    public ResponseEntity<Persona> savePersona(@RequestBody Persona persona) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personaRepository.save(persona));
    }

    @Operation
    @PutMapping("/{id}")
    public ResponseEntity<Persona> putPersona(@PathVariable Long id, @RequestBody Persona persona){
        return personaRepository.findById(id)
                .map(p -> personaRepository.save(persona))
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable Long id) {
        return personaRepository.findById(id)
                .map(p -> {
                    personaRepository.delete(p);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(); // TODO
    }
}
