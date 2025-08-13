package com.example.hospital.controllers;

import com.example.hospital.Routes;
import com.example.hospital.entities.District;
import com.example.hospital.exceptions.NotFoundException;
import com.example.hospital.services.LocalidadService;
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
@Tag(name = "ABM Localidad")
@SecurityRequirement(name = "bearer-key")
@RequestMapping(path = Routes.LOCALIDAD_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LocalidadController {
    private final LocalidadService localidadService;

    public LocalidadController(LocalidadService localidadService) {
        this.localidadService = localidadService;
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Listar Localidades")
    @GetMapping
    public List<District> findAllLocalidades(
            @RequestParam(defaultValue = "false") boolean showDeleted
    ) {
        return localidadService.findAll(showDeleted);
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Detalle Localidad")
    @GetMapping("/{id}")
    public ResponseEntity<District> findOneLocalidad(@PathVariable Long id) throws NotFoundException {
        District localidad = localidadService.findById(id);
        if(localidad != null)
            return ResponseEntity.ok(localidad);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Crear Localidad")
    @PostMapping
    public ResponseEntity<District> saveLocalidad(@RequestBody District localidad) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(localidadService.save(localidad));
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Editar Localidad")
    @PutMapping("/{id}")
    public ResponseEntity<District> putLocalidad(@PathVariable Long id, @RequestBody District localidad) throws NotFoundException {
        District entity = localidadService.update(id, localidad);
        if(entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Borrar Localidad")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLocalidad(@PathVariable Long id) throws NotFoundException {
        District entity = localidadService.delete(id);
        if(entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }
}
