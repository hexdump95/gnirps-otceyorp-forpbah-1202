package com.example.hospital.services;

import com.example.hospital.dtos.RequestDetailsDto;
import com.example.hospital.dtos.RequestAppointmentDto;
import com.example.hospital.dtos.RequestDto;
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
    public List<RequestDto> buscarTodasSolicitudTurno(boolean showAll) {
        List<AppointmentRequest> solicitudes;
        if (showAll)
            solicitudes = this.solicitudTurnoRepository.findAll();
        else
            solicitudes = this.solicitudTurnoRepository.findAllByStatusPendiente();
        return solicitudes.stream()
                .map(st -> modelMapper.map(st, RequestDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDetailsDto> buscarMisSolicitudTurno(String userId) {
        return solicitudTurnoRepository.findAllByPatientUserId(UUID.fromString(userId))
                .stream().map(st -> modelMapper.map(st, RequestDetailsDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public RequestDetailsDto findOneSolicitudTurno(Long id) {
        return solicitudTurnoRepository.findById(id)
        .map(st -> modelMapper.map(st, RequestDetailsDto.class)).orElse(null);
    }

    @Override
    public RequestDetailsDto findOneSolicitudTurnoPaciente(Long id, String userId) {
        return solicitudTurnoRepository.findByIdAndPatientUserId(id
                , UUID.fromString(userId)
        ).map(st -> modelMapper.map(st, RequestDetailsDto.class)).orElse(null);
    }

    @Override
    public RequestDetailsDto solicitarTurno(RequestAppointmentDto requestAppointmentDto, String userId) {

        RequestStatus requestStatus = estadoSolicitudRepository.findByName("Pendiente de Aprobación");

        StatusRequest se = new StatusRequest();
        se.setFromDate(LocalDateTime.now());
        se.setRequestStatus(requestStatus);

        AppointmentRequest appointmentRequest = new AppointmentRequest();
        Specialty specialty = new Specialty();
        specialty.setId(requestAppointmentDto.getSpecialty().getId());
        appointmentRequest.setSpecialty(specialty);
        appointmentRequest.getStatusRequests().add(se);

        Person patient = personaRepository.findByUserId(UUID.fromString(userId));

        Address address = new Address();
        address.setId(requestAppointmentDto.getAddressDto().getId());
        patient.setAddress(address);

        personaRepository.save(patient);
        appointmentRequest.setPatient(patient);

        appointmentRequest = solicitudTurnoRepository.save(appointmentRequest);

        return modelMapper.map(appointmentRequest, RequestDetailsDto.class);
    }

    @Override
    public RequestDetailsDto rechazarSolicitud(Long id) {
        return solicitudTurnoRepository.findById(id)
                .map(st -> {
                    LocalDateTime fechaHoraActual = LocalDateTime.now();
                    if (ultimoEstado(fechaHoraActual, st)) {
                        RequestStatus rechazado = estadoSolicitudRepository.findByName("Rechazada");
                        StatusRequest solicitudEstadoRechazado = new StatusRequest();
                        solicitudEstadoRechazado.setRequestStatus(rechazado);
                        st.getStatusRequests().add(solicitudEstadoRechazado);
                    }
                    return solicitudTurnoRepository.save(st);
                })
                .map(st -> modelMapper.map(st, RequestDetailsDto.class))
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

    private boolean ultimoEstado(LocalDateTime time, AppointmentRequest st) {
        boolean ultimoEstado = false;
        for (StatusRequest se : st.getStatusRequests()) {
            if (se.getRequestStatus().getName().matches("Pendiente de Aprobación")
                    && se.getToDate() == null) {
                se.setToDate(time);
                ultimoEstado = true;
                break;
            }
        }
        return ultimoEstado;
    }

}
