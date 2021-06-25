package com.example.hospital.controllers;

import com.example.hospital.entities.EstadoSolicitud;
import com.example.hospital.repositories.EstadoSolicitudRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "ABM EstadoSolicitud")
@RequestMapping(path = "/estadosolicitudes", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoSolicitudController {
    private final EstadoSolicitudRepository estadoSolicitudRepository;

    public EstadoSolicitudController(EstadoSolicitudRepository estadoSolicitudRepository) {
        this.estadoSolicitudRepository = estadoSolicitudRepository;
    }

    @Operation
    @GetMapping
    public List<EstadoSolicitud> findAllEstadoSolicitudes() {
        return estadoSolicitudRepository.findAll();
    }

    @Operation
    @GetMapping("/{id}")
    public ResponseEntity<EstadoSolicitud> findOneEstadoSolicitud(@PathVariable Long id) {
        return estadoSolicitudRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @PostMapping
    public ResponseEntity<EstadoSolicitud> saveEstadoSolicitud(@RequestBody EstadoSolicitud estadoSolicitud) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estadoSolicitudRepository.save(estadoSolicitud));
    }

    @Operation
    @PutMapping("/{id}")
    public ResponseEntity<EstadoSolicitud> putEstadoSolicitud(@PathVariable Long id, @RequestBody EstadoSolicitud estadoSolicitud){
        return estadoSolicitudRepository.findById(id)
                .map(es -> estadoSolicitudRepository.save(estadoSolicitud))
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstadoSolicitud(@PathVariable Long id) {
        return estadoSolicitudRepository.findById(id)
                .map(es -> {
                    estadoSolicitudRepository.delete(es);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(); // TODO
    }
}
