package com.example.hospital.controllers;

import com.example.hospital.Routes;
import com.example.hospital.entities.Especialidad;
import com.example.hospital.exceptions.NotFoundException;
import com.example.hospital.services.EspecialidadService;
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
@Tag(name = "ABM Especialidad")
@RequestMapping(path = Routes.ESPECIALIDAD_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EspecialidadController {
    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @Operation(summary = "Listar Especialidades")
    @GetMapping
    public List<Especialidad> findAllEspecialidades(
            @RequestParam(defaultValue = "false") boolean showDeleted
    ) {
        return especialidadService.findAll(showDeleted);
    }

    @Operation(summary = "Detalle Especialidad")
    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> findOneEspecialidad(@PathVariable Long id) throws NotFoundException {
        Especialidad especialidad = especialidadService.findById(id);
        if(especialidad != null)
            return ResponseEntity.ok(especialidad);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Crear Especialidad")
    @SecurityRequirement(name = "bearer-key")
    @PostMapping
    public ResponseEntity<Especialidad> saveEspecialidad(@RequestBody Especialidad especialidad) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(especialidadService.save(especialidad));
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Editar Especialidad")
    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> putEspecialidad(@PathVariable Long id, @RequestBody Especialidad especialidad) throws NotFoundException {
        Especialidad entity = especialidadService.update(id, especialidad);
        if(entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Borrar Especialidad")
    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEspecialidad(@PathVariable Long id) throws NotFoundException {
        Especialidad entity = especialidadService.delete(id);
        if(entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }
}
