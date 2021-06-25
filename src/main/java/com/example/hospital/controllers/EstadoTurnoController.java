package com.example.hospital.controllers;

import com.example.hospital.entities.EstadoTurno;
import com.example.hospital.repositories.EstadoTurnoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "ABM EstadoTurno")
@RequestMapping(path = "/estadoturnos", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoTurnoController {
    private final EstadoTurnoRepository estadoTurnoRepository;

    public EstadoTurnoController(EstadoTurnoRepository estadoTurnoRepository) {
        this.estadoTurnoRepository = estadoTurnoRepository;
    }

    @Operation
    @GetMapping
    public List<EstadoTurno> findAllEstadoTurnos() {
        return estadoTurnoRepository.findAll();
    }

    @Operation
    @GetMapping("/{id}")
    public ResponseEntity<EstadoTurno> findOneEstadoTurno(@PathVariable Long id) {
        return estadoTurnoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @PostMapping
    public ResponseEntity<EstadoTurno> saveEstadoTurno(@RequestBody EstadoTurno estadoTurno) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estadoTurnoRepository.save(estadoTurno));
    }

    @Operation
    @PutMapping("/{id}")
    public ResponseEntity<EstadoTurno> putEstadoTurno(@PathVariable Long id, @RequestBody EstadoTurno estadoTurno){
        return estadoTurnoRepository.findById(id)
                .map(et -> estadoTurnoRepository.save(estadoTurno))
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstadoTurno(@PathVariable Long id) {
        return estadoTurnoRepository.findById(id)
                .map(et -> {
                    estadoTurnoRepository.delete(et);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(); // TODO
    }
}
