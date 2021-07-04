package com.example.hospital.services;

import com.example.hospital.entities.Localidad;
import com.example.hospital.repositories.LocalidadRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LocalidadServiceImpl implements LocalidadService {

    private final LocalidadRepository localidadRepository;

    public LocalidadServiceImpl(LocalidadRepository localidadRepository) {
        this.localidadRepository = localidadRepository;
    }

    @Override
    public List<Localidad> findAll(boolean showDeleted) {
        if(showDeleted)
            return localidadRepository.findAll();
        else return localidadRepository.findByFechaBajaLocalidadIsNull();
    }

    @Override
    public Localidad findById(Long id) {
        return localidadRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public Localidad save(Localidad localidad) {
        return localidadRepository
                .save(localidad);
    }

    @Override
    public Localidad update(Long id, Localidad localidad) {
        return localidadRepository.findById(id)
                .map(l -> localidadRepository.save(localidad))
                .orElse(null);
    }

    @Override
    public Localidad delete(Long id) {
        return localidadRepository.findById(id)
                .map(l -> {
                    l.setFechaBajaLocalidad(LocalDateTime.now());
                    return localidadRepository.save(l);
                }).orElse(null);
    }

}
