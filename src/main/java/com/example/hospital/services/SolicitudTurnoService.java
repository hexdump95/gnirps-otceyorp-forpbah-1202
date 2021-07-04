package com.example.hospital.services;

import com.example.hospital.dtos.DetalleSolicitudDto;
import com.example.hospital.dtos.SolicitarTurnoDto;
import com.example.hospital.dtos.SolicitudDto;

import java.util.List;

public interface SolicitudTurnoService {
    List<SolicitudDto> buscarTodasSolicitudTurno(boolean showAll);
    List<DetalleSolicitudDto> buscarMisSolicitudTurno(String userId);
    DetalleSolicitudDto findOneSolicitudTurno(Long id);
    DetalleSolicitudDto findOneSolicitudTurnoPaciente(Long id, String userId);
    DetalleSolicitudDto solicitarTurno(SolicitarTurnoDto solicitudTurnoDto, String userId);
    DetalleSolicitudDto rechazarSolicitud(Long id);
    boolean cancelarSolicitud(Long id);
}
