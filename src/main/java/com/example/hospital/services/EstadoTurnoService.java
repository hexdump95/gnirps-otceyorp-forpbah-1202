package com.example.hospital.services;

import com.example.hospital.entities.EstadoTurno;

import java.util.List;

public interface EstadoTurnoService {
    List<EstadoTurno> findAll(boolean showDeleted);
    EstadoTurno findById(Long id);
    EstadoTurno save(EstadoTurno estadoTurno);
    EstadoTurno update(Long id, EstadoTurno estadoTurno);
    EstadoTurno delete(Long id);
}
