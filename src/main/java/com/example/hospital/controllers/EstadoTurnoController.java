package com.example.hospital.controllers;

import com.example.hospital.Routes;
import com.example.hospital.entities.EstadoTurno;
import com.example.hospital.exceptions.NotFoundException;
import com.example.hospital.services.EstadoTurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "ABM EstadoTurno")
@RequestMapping(path = Routes.ESTADOTURNO_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoTurnoController {
    private final EstadoTurnoService estadoTurnoService;

    public EstadoTurnoController(EstadoTurnoService estadoTurnoService) {
        this.estadoTurnoService = estadoTurnoService;
    }

    @PreAuthorize(value = "hasAnyRole('MEDICO', 'RECEPCIONISTA', 'ADMIN')")
    @Operation(summary = "Listar EstadoTurnoes")
    @GetMapping
    public List<EstadoTurno> findAllEstadoTurnoes(
            @RequestParam(defaultValue = "false") boolean showDeleted
    ) {
        return estadoTurnoService.findAll(showDeleted);
    }

    @PreAuthorize(value = "hasAnyRole('MEDICO', 'RECEPCIONISTA', 'ADMIN')")
    @Operation(summary = "Detalle EstadoTurno")
    @GetMapping("/{id}")
    public ResponseEntity<EstadoTurno> findOneEstadoTurno(@PathVariable Long id) throws NotFoundException {
        EstadoTurno estadoTurno = estadoTurnoService.findById(id);
        if(estadoTurno != null)
            return ResponseEntity.ok(estadoTurno);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Crear EstadoTurno")
    @SecurityRequirement(name = "bearer-key")
    @PostMapping
    public ResponseEntity<EstadoTurno> saveEstadoTurno(@RequestBody EstadoTurno estadoTurno) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estadoTurnoService.save(estadoTurno));
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Editar EstadoTurno")
    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    public ResponseEntity<EstadoTurno> putEstadoTurno(@PathVariable Long id, @RequestBody EstadoTurno estadoTurno) throws NotFoundException {
        EstadoTurno entity = estadoTurnoService.update(id, estadoTurno);
        if(entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Borrar EstadoTurno")
    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEstadoTurno(@PathVariable Long id) throws NotFoundException {
        EstadoTurno entity = estadoTurnoService.delete(id);
        if(entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }
}
