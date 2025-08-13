package com.example.hospital.controllers;

import com.example.hospital.Routes;
import com.example.hospital.dtos.RequestDetailsDto;
import com.example.hospital.dtos.RequestAppointmentDto;
import com.example.hospital.dtos.RequestDto;
import com.example.hospital.exceptions.NotFoundException;
import com.example.hospital.services.SolicitudTurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Solicitud Turno")
@SecurityRequirement(name = "bearer-key")
@RequestMapping(path = Routes.SOLICITUDTURNO_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SolicitudTurnoController {
    private final SolicitudTurnoService solicitudTurnoService;

    public SolicitudTurnoController(SolicitudTurnoService solicitudTurnoService) {
        this.solicitudTurnoService = solicitudTurnoService;
    }

    @PreAuthorize(value = "hasAnyRole('MEDICO', 'RECEPCIONISTA', 'ADMIN')")
    @Operation
    @GetMapping("/admin") // TODO
    public List<RequestDto> findAllSolicitudTurnos(
            @RequestParam(defaultValue = "false") boolean showAll
    ) {
        return solicitudTurnoService.buscarTodasSolicitudTurno(showAll);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @Operation
    @GetMapping
    public List<RequestDetailsDto> findAllMySolicitudTurnos(
            @Parameter(hidden = true) @CurrentSecurityContext(expression = "authentication.name") String userId
    ) {
        return solicitudTurnoService.buscarMisSolicitudTurno(userId);
    }

    @PreAuthorize(value = "hasAnyRole('MEDICO', 'RECEPCIONISTA', 'ADMIN')")
    @Operation
    @GetMapping("admin/{id}") // TODO
    public ResponseEntity<RequestDetailsDto> findOneSolicitudTurno(@PathVariable Long id)
            throws NotFoundException {
        RequestDetailsDto entity = solicitudTurnoService.findOneSolicitudTurno(id);
        if (entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('SOCIO', 'MEDICO', 'RECEPCIONISTA', 'ADMIN')")
    @Operation
    @GetMapping("/{id}")
    public ResponseEntity<RequestDetailsDto> findOneSolicitudTurnoSocio(
            @PathVariable Long id,
            @Parameter(hidden = true) @CurrentSecurityContext(expression = "authentication.name") String userId
    ) throws NotFoundException {
        RequestDetailsDto entity = solicitudTurnoService.findOneSolicitudTurnoPaciente(id, userId);
        if (entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('SOCIO', 'MEDICO', 'RECEPCIONISTA', 'ADMIN')")
    @Operation(summary = "Solicitar turno")
    @PostMapping
    public ResponseEntity<RequestDetailsDto> saveSolicitudTurno(
            @Parameter(hidden = true) @CurrentSecurityContext(expression = "authentication.name") String userId,
            @RequestBody RequestAppointmentDto solicitudTurno
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(solicitudTurnoService.solicitarTurno(solicitudTurno, userId));
    }

    @PreAuthorize(value = "hasAnyRole('MEDICO', 'RECEPCIONISTA', 'ADMIN')")
    @Operation(summary = "Rechazar Solicitud de Turno")
    @PutMapping("/{id}/rechazar")
    public ResponseEntity<RequestDetailsDto> rechazarSolicitudTurno(@PathVariable Long id) throws NotFoundException {
        RequestDetailsDto entity = solicitudTurnoService.rechazarSolicitud(id);
        if (entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('SOCIO', 'MEDICO', 'RECEPCIONISTA', 'ADMIN')")
    @Operation(summary = "Cancelar Solicitud de Turno")
    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<Object> cancelarSolicitudTurno(@PathVariable Long id) throws NotFoundException {
        boolean isDeleted = solicitudTurnoService.cancelarSolicitud(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else throw new NotFoundException(id);
    }
}
