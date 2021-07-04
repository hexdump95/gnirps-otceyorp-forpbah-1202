package com.example.hospital.services;

import com.example.hospital.dtos.DetalleSolicitudDto;
import com.example.hospital.dtos.SolicitarTurnoDto;
import com.example.hospital.dtos.SolicitudDto;
import com.example.hospital.entities.*;
import com.example.hospital.repositories.EstadoSolicitudRepository;
import com.example.hospital.repositories.PersonaRepository;
import com.example.hospital.repositories.SolicitudTurnoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SolicitudTurnoServiceImpl implements SolicitudTurnoService {
    private final PersonaRepository personaRepository;
    private final SolicitudTurnoRepository solicitudTurnoRepository;
    private final EstadoSolicitudRepository estadoSolicitudRepository;
    private final ModelMapper modelMapper;

    public SolicitudTurnoServiceImpl(
            PersonaRepository personaRepository,
            SolicitudTurnoRepository solicitudTurnoRepository,
            EstadoSolicitudRepository estadoSolicitudRepository,
            ModelMapper modelMapper) {
        this.personaRepository = personaRepository;
        this.solicitudTurnoRepository = solicitudTurnoRepository;
        this.estadoSolicitudRepository = estadoSolicitudRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SolicitudDto> buscarTodasSolicitudTurno(boolean showAll) {
        List<SolicitudTurno> solicitudes;
        if (showAll)
            solicitudes = this.solicitudTurnoRepository.findAll();
        else
            solicitudes = this.solicitudTurnoRepository.findAllByEstadoSolicitudPendiente();
        return solicitudes.stream()
                .map(st -> modelMapper.map(st, SolicitudDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DetalleSolicitudDto> buscarMisSolicitudTurno(String userId) {
        return solicitudTurnoRepository.findAllByPacienteUsuarioId(UUID.fromString(userId))
                .stream().map(st -> modelMapper.map(st, DetalleSolicitudDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public DetalleSolicitudDto findOneSolicitudTurno(Long id) {
        return solicitudTurnoRepository.findById(id)
        .map(st -> modelMapper.map(st, DetalleSolicitudDto.class)).orElse(null);
    }

    @Override
    public DetalleSolicitudDto findOneSolicitudTurnoPaciente(Long id, String userId) {
        return solicitudTurnoRepository.findByIdAndPacienteUsuarioId(id
                , UUID.fromString(userId)
        ).map(st -> modelMapper.map(st, DetalleSolicitudDto.class)).orElse(null);
    }

    @Override
    public DetalleSolicitudDto solicitarTurno(SolicitarTurnoDto solicitudTurnoDto, String userId) {

        EstadoSolicitud estadoSolicitud = estadoSolicitudRepository.findByNombreEstadoSolicitud("Pendiente de Aprobación");

        SolicitudEstado se = new SolicitudEstado();
        se.setFechaDesdeSolicitudEstado(LocalDateTime.now());
        se.setEstadoSolicitud(estadoSolicitud);

        SolicitudTurno solicitudTurno = new SolicitudTurno();
        Especialidad especialidad = new Especialidad();
        especialidad.setId(solicitudTurnoDto.getEspecialidad().getId());
        solicitudTurno.setEspecialidad(especialidad);
        solicitudTurno.getSolicitudEstadoList().add(se);

        Persona paciente = personaRepository.findByUsuarioId(UUID.fromString(userId));

        Domicilio domicilio = new Domicilio();
        domicilio.setId(solicitudTurnoDto.getDomicilioDto().getId());
        paciente.setDomicilio(domicilio);

        personaRepository.save(paciente);
        solicitudTurno.setPaciente(paciente);

        SolicitudTurno solicitud = solicitudTurnoRepository.save(solicitudTurno);

        return modelMapper.map(solicitud, DetalleSolicitudDto.class);
    }

    @Override
    public DetalleSolicitudDto rechazarSolicitud(Long id) {
        return solicitudTurnoRepository.findById(id)
                .map(st -> {
                    LocalDateTime fechaHoraActual = LocalDateTime.now();
                    if (ultimoEstado(fechaHoraActual, st)) {
                        EstadoSolicitud rechazado = estadoSolicitudRepository.findByNombreEstadoSolicitud("Rechazada");
                        SolicitudEstado solicitudEstadoRechazado = new SolicitudEstado();
                        solicitudEstadoRechazado.setEstadoSolicitud(rechazado);
                        st.getSolicitudEstadoList().add(solicitudEstadoRechazado);
                    }
                    return solicitudTurnoRepository.save(st);
                })
                .map(st -> modelMapper.map(st, DetalleSolicitudDto.class))
                .orElse(null);
    }

    @Override
    public boolean cancelarSolicitud(Long id) {
        return solicitudTurnoRepository.findById(id)
                .map(st -> {
                    LocalDateTime fechaHoraActual = LocalDateTime.now();
                    if (ultimoEstado(fechaHoraActual, st)) {
                        solicitudTurnoRepository.delete(st);
                        return true;
                    } else return false;
                })
                .orElse(false);
    }

    private boolean ultimoEstado(LocalDateTime time, SolicitudTurno st) {
        boolean ultimoEstado = false;
        for (SolicitudEstado se : st.getSolicitudEstadoList()) {
            if (se.getEstadoSolicitud().getNombreEstadoSolicitud().matches("Pendiente de Aprobación")
                    && se.getFechaHastaSolicitudEstado() == null) {
                se.setFechaHastaSolicitudEstado(time);
                ultimoEstado = true;
                break;
            }
        }
        return ultimoEstado;
    }

}
