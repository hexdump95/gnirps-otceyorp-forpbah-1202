package com.example.hospital.controllers;

import com.example.hospital.dtos.SolicitudTurnoDto;
import com.example.hospital.entities.EstadoSolicitud;
import com.example.hospital.entities.SolicitudEstado;
import com.example.hospital.entities.SolicitudTurno;
import com.example.hospital.repositories.EstadoSolicitudRepository;
import com.example.hospital.repositories.SolicitudTurnoRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/solicitudturnos", produces = MediaType.APPLICATION_JSON_VALUE)
public class SolicitudTurnoController {
    private final SolicitudTurnoRepository solicitudTurnoRepository;
    private final EstadoSolicitudRepository estadoSolicitudRepository;
    private final ModelMapper modelMapper;

    public SolicitudTurnoController(
            SolicitudTurnoRepository solicitudTurnoRepository,
            EstadoSolicitudRepository estadoSolicitudRepository,
            ModelMapper modelMapper) {
        this.solicitudTurnoRepository = solicitudTurnoRepository;
        this.estadoSolicitudRepository = estadoSolicitudRepository;
        this.modelMapper = modelMapper;
    }

    @Operation
    @GetMapping
    public List<SolicitudTurno> findAllSolicitudTurnos() {
        return solicitudTurnoRepository.findAll();
    }

    @Operation
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudTurno> findOneSolicitudTurno(@PathVariable Long id) {
        return solicitudTurnoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation(summary = "Solicitar turno")
    @PostMapping
    public ResponseEntity<SolicitudTurno> saveSolicitudTurno(
            @RequestBody SolicitudTurnoDto solicitudTurnoDto
    ) {
        SolicitudTurno solicitudTurno = modelMapper.map(solicitudTurnoDto, SolicitudTurno.class);

        EstadoSolicitud estadoSolicitud = estadoSolicitudRepository.findByNombreEstadoSolicitud("Pendiente");

        SolicitudEstado se = new SolicitudEstado();
        se.setFechaDesdeSolicitudEstado(LocalDateTime.now());
        se.setEstadoSolicitud(estadoSolicitud);

        solicitudTurno.setFechaSolicitudTurno(LocalDateTime.now());
        solicitudTurno.getSolicitudEstadoList().add(se);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(solicitudTurnoRepository.save(solicitudTurno));
    }

    @Operation(summary = "Rechazar Solicitud de Turno")
    @PutMapping("/{id}")
    public ResponseEntity<SolicitudTurno> putSolicitudTurno(@PathVariable Long id) {
        return solicitudTurnoRepository.findById(id)
                .map(st -> {
                    boolean ultimoEstadoEsPendiente = false;
                    LocalDateTime fechaHoraActual = LocalDateTime.now();
                    for (SolicitudEstado se : st.getSolicitudEstadoList()) {
                        if (se.getEstadoSolicitud().getNombreEstadoSolicitud().matches("Pendiente")
                                && se.getFechaHastaSolicitudEstado() == null) {
                            se.setFechaHastaSolicitudEstado(fechaHoraActual);
                            ultimoEstadoEsPendiente = true;
                            break;
                        }
                    }
                    if(ultimoEstadoEsPendiente){

                        EstadoSolicitud rechazado = estadoSolicitudRepository.findByNombreEstadoSolicitud("Rechazado");
                        SolicitudEstado solicitudEstadoRechazado = new SolicitudEstado();
                        solicitudEstadoRechazado.setFechaDesdeSolicitudEstado(fechaHoraActual);
                        solicitudEstadoRechazado.setEstadoSolicitud(rechazado);
                        st.getSolicitudEstadoList().add(solicitudEstadoRechazado);
                    }
                    return solicitudTurnoRepository.save(st);
                })
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation(summary = "Cancelar Solicitud de Turno")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarSolicitudTurno(@PathVariable Long id) {
        return solicitudTurnoRepository.findById(id)
                .map(st -> {
                    boolean ultimoEstadoEsPendiente = false;
                    LocalDateTime fechaHoraActual = LocalDateTime.now();
                    for (SolicitudEstado se : st.getSolicitudEstadoList()) {
                        if (se.getEstadoSolicitud().getNombreEstadoSolicitud().matches("Pendiente")
                                && se.getFechaHastaSolicitudEstado() == null) {
                            se.setFechaHastaSolicitudEstado(fechaHoraActual);
                            ultimoEstadoEsPendiente = true;
                            break;
                        }
                    }
                    if(ultimoEstadoEsPendiente) {
                        solicitudTurnoRepository.delete(st);
                    }
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(); // TODO
    }
}
