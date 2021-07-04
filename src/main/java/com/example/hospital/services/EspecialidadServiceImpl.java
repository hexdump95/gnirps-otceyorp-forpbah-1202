package com.example.hospital.services;

import com.example.hospital.entities.Especialidad;
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
    public List<Especialidad> findAll(boolean showDeleted) {
        if(showDeleted)
            return especialidadRepository.findAll();
        else return especialidadRepository.findByFechaBajaEspecialidadIsNull();
    }

    @Override
    public Especialidad findById(Long id) {
        return especialidadRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public Especialidad save(Especialidad especialidad) {
        return especialidadRepository
                .save(especialidad);
    }

    @Override
    public Especialidad update(Long id, Especialidad especialidad) {
        return especialidadRepository.findById(id)
                .map(e -> especialidadRepository.save(especialidad))
                .orElse(null);
    }

    @Override
    public Especialidad delete(Long id) {
        return especialidadRepository.findById(id)
                .map(e -> {
                    e.setFechaBajaEspecialidad(LocalDateTime.now());
                    return especialidadRepository.save(e);
                }).orElse(null);
    }


}
