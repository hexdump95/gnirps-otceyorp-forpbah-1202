package com.example.hospital.controllers;

import com.example.hospital.entities.EstadoTurno;
import com.example.hospital.entities.Turno;
import com.example.hospital.repositories.EstadoTurnoRepository;
import com.example.hospital.repositories.TurnoRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/turnos", produces = MediaType.APPLICATION_JSON_VALUE)
public class TurnoController {
    private final TurnoRepository turnoRepository;
    private final EstadoTurnoRepository estadoTurnoRepository;

    public TurnoController(TurnoRepository turnoRepository,
                           EstadoTurnoRepository estadoTurnoRepository) {
        this.turnoRepository = turnoRepository;
        this.estadoTurnoRepository = estadoTurnoRepository;
    }

    @Operation
    @GetMapping
    public List<Turno> findAllTurnos() {
        return turnoRepository.findAll();
    }

    @Operation
    @GetMapping("/{id}")
    public ResponseEntity<Turno> findOneTurno(@PathVariable Long id) {
        return turnoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation(summary = "Crear Turno")
    @PostMapping
    public ResponseEntity<Turno> saveTurno(@RequestBody Turno turno) {
        EstadoTurno enEspera = estadoTurnoRepository.findByNombreEstadoTurno("En espera");
        turno.setEstadoTurno(enEspera);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(turnoRepository.save(turno));
    }

    @Operation
    @PutMapping("/{id}")
    public ResponseEntity<Turno> putTurno(@PathVariable Long id, @RequestBody Turno turno){
        return turnoRepository.findById(id)
                .map(t -> turnoRepository.save(turno))
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTurno(@PathVariable Long id) {
        return turnoRepository.findById(id)
                .map(t -> {
                    turnoRepository.delete(t);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(); // TODO
    }
}
