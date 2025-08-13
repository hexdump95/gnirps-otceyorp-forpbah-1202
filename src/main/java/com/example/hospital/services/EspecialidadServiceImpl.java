package com.example.hospital.services;

import com.example.hospital.entities.Specialty;
import com.example.hospital.repositories.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {
    private final EspecialidadRepository especialidadRepository;

    public EspecialidadServiceImpl(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public List<Specialty> findAll(boolean showDeleted) {
        if(showDeleted)
            return especialidadRepository.findAll();
        else return especialidadRepository.findByDeletedAtIsNull();
    }

    @Override
    public Specialty findById(Long id) {
        return especialidadRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public Specialty save(Specialty specialty) {
        return especialidadRepository
                .save(specialty);
    }

    @Override
    public Specialty update(Long id, Specialty specialty) {
        return especialidadRepository.findById(id)
                .map(e -> especialidadRepository.save(specialty))
                .orElse(null);
    }

    @Override
    public Specialty delete(Long id) {
        return especialidadRepository.findById(id)
                .map(e -> {
                    e.setDeletedAt(LocalDateTime.now());
                    return especialidadRepository.save(e);
                }).orElse(null);
    }


}
