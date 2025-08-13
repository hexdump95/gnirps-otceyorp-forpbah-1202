package com.example.hospital.services;

import com.example.hospital.entities.Specialty;

import java.util.List;

public interface EspecialidadService {
    List<Specialty> findAll(boolean showDeleted);
    Specialty findById(Long id);
    Specialty save(Specialty specialty);
    Specialty update(Long id, Specialty specialty);
    Specialty delete(Long id);
}
