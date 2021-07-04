package com.example.hospital.controllers;

import com.example.hospital.Routes;
import com.example.hospital.entities.EstadoSolicitud;
import com.example.hospital.exceptions.NotFoundException;
import com.example.hospital.services.EstadoSolicitudService;
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
@Tag(name = "ABM EstadoSolicitud")
@RequestMapping(path = Routes.ESTADOSOLICITUD_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoSolicitudController {
    private final EstadoSolicitudService estadoSolicitudService;

    public EstadoSolicitudController(EstadoSolicitudService estadoSolicitudService) {
        this.estadoSolicitudService = estadoSolicitudService;
    }

    @PreAuthorize(value = "hasAnyRole('MEDICO', 'RECEPCIONISTA', 'ADMIN')")
    @Operation(summary = "Listar EstadoSolicitudes")
    @GetMapping
    public List<EstadoSolicitud> findAllEstadoSolicitudes(
            @RequestParam(defaultValue = "false") boolean showDeleted
    ) {
        return estadoSolicitudService.findAll(showDeleted);
    }

    @PreAuthorize(value = "hasAnyRole('MEDICO', 'RECEPCIONISTA', 'ADMIN')")
    @Operation(summary = "Detalle EstadoSolicitud")
    @GetMapping("/{id}")
    public ResponseEntity<EstadoSolicitud> findOneEstadoSolicitud(@PathVariable Long id) throws NotFoundException {
        EstadoSolicitud estadoSolicitud = estadoSolicitudService.findById(id);
        if(estadoSolicitud != null)
            return ResponseEntity.ok(estadoSolicitud);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Crear EstadoSolicitud")
    @SecurityRequirement(name = "bearer-key")
    @PostMapping
    public ResponseEntity<EstadoSolicitud> saveEstadoSolicitud(@RequestBody EstadoSolicitud estadoSolicitud) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estadoSolicitudService.save(estadoSolicitud));
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Editar EstadoSolicitud")
    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    public ResponseEntity<EstadoSolicitud> putEstadoSolicitud(@PathVariable Long id, @RequestBody EstadoSolicitud estadoSolicitud) throws NotFoundException {
        EstadoSolicitud entity = estadoSolicitudService.update(id, estadoSolicitud);
        if(entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Borrar EstadoSolicitud")
    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEstadoSolicitud(@PathVariable Long id) throws NotFoundException {
        EstadoSolicitud entity = estadoSolicitudService.delete(id);
        if(entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }
}
