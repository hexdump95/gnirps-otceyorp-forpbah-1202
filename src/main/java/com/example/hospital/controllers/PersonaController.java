package com.example.hospital.controllers;

import com.example.hospital.Routes;
import com.example.hospital.dtos.PersonaDto;
import com.example.hospital.entities.Persona;
import com.example.hospital.repositories.PersonaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "usuarios")
@SecurityRequirement(name = "bearer-key")
@RequestMapping(path = Routes.PERSONA_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonaController {
    private final PersonaRepository personaRepository;
    private final ModelMapper modelMapper;

    public PersonaController(PersonaRepository personaRepository,
                             ModelMapper modelMapper) {
        this.personaRepository = personaRepository;
        this.modelMapper = modelMapper;
    }

    @Operation
    @GetMapping
    public List<Persona> findAllPersonas() {
        return personaRepository.findAll();
    }

    @Operation
    @GetMapping("/especialidades/{especialidadId}")
    public List<PersonaDto> findMedicoByEspecialidad(@PathVariable Long especialidadId) {
        return personaRepository.findAllByMedicoEspecialidadId(especialidadId)
                .stream().map(p -> modelMapper.map(p, PersonaDto.class))
                .collect(Collectors.toList());
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
