package com.example.hospital.services;
import com.example.hospital.entities.Localidad;

import java.util.List;

public interface LocalidadService {
    List<Localidad> findAll(boolean showDeleted);
    Localidad findById(Long id);
    Localidad save(Localidad localidad);
    Localidad update(Long id, Localidad localidad);
    Localidad delete(Long id);
}
