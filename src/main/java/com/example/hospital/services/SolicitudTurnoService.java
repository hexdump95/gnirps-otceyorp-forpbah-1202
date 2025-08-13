package com.example.hospital.services;

import com.example.hospital.dtos.RequestDetailsDto;
import com.example.hospital.dtos.RequestAppointmentDto;
import com.example.hospital.dtos.RequestDto;

import java.util.List;

public interface SolicitudTurnoService {
    List<RequestDto> buscarTodasSolicitudTurno(boolean showAll);
    List<RequestDetailsDto> buscarMisSolicitudTurno(String userId);
    RequestDetailsDto findOneSolicitudTurno(Long id);
    RequestDetailsDto findOneSolicitudTurnoPaciente(Long id, String userId);
    RequestDetailsDto solicitarTurno(RequestAppointmentDto requestAppointmentDto, String userId);
    RequestDetailsDto rechazarSolicitud(Long id);
    boolean cancelarSolicitud(Long id);
}
