package com.example.hospital.services;

import com.example.hospital.entities.District;
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
    public List<District> findAll(boolean showDeleted) {
        if(showDeleted)
            return localidadRepository.findAll();
        else return localidadRepository.findByDeletedAtIsNull();
    }

    @Override
    public District findById(Long id) {
        return localidadRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public District save(District localidad) {
        return localidadRepository
                .save(localidad);
    }

    @Override
    public District update(Long id, District localidad) {
        return localidadRepository.findById(id)
                .map(l -> localidadRepository.save(localidad))
                .orElse(null);
    }

    @Override
    public District delete(Long id) {
        return localidadRepository.findById(id)
                .map(l -> {
                    l.setDeletedAt(LocalDateTime.now());
                    return localidadRepository.save(l);
                }).orElse(null);
    }

}
