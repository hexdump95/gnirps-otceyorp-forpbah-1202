package com.example.hospital.services;

import com.example.hospital.entities.EstadoSolicitud;

import java.util.List;

public interface EstadoSolicitudService {
    List<EstadoSolicitud> findAll(boolean showDeleted);
    EstadoSolicitud findById(Long id);
    EstadoSolicitud save(EstadoSolicitud estadoSolicitud);
    EstadoSolicitud update(Long id, EstadoSolicitud estadoSolicitud);
    EstadoSolicitud delete(Long id);
}
