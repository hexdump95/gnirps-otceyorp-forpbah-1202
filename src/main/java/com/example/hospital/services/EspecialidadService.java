package com.example.hospital.services;

import com.example.hospital.entities.Especialidad;

import java.util.List;

public interface EspecialidadService {
    List<Especialidad> findAll(boolean showDeleted);
    Especialidad findById(Long id);
    Especialidad save(Especialidad especialidad);
    Especialidad update(Long id, Especialidad especialidad);
    Especialidad delete(Long id);
}
