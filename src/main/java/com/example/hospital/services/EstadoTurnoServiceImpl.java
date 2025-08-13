package com.example.hospital.services;

import com.example.hospital.entities.AppointmentStatus;
import com.example.hospital.repositories.EstadoTurnoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstadoTurnoServiceImpl implements EstadoTurnoService {
    private final EstadoTurnoRepository estadoTurnoRepository;

    public EstadoTurnoServiceImpl(EstadoTurnoRepository estadoTurnoRepository) {
        this.estadoTurnoRepository = estadoTurnoRepository;
    }

    @Override
    public List<AppointmentStatus> findAll(boolean showDeleted) {
        if(showDeleted)
        return estadoTurnoRepository.findAll();
        else return estadoTurnoRepository.findByDeletedAtIsNull();
    }

    @Override
    public AppointmentStatus findById(Long id) {
        return estadoTurnoRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public AppointmentStatus save(AppointmentStatus appointmentStatus) {
        return estadoTurnoRepository
                .save(appointmentStatus);
    }

    @Override
    public AppointmentStatus update(Long id, AppointmentStatus appointmentStatus) {
        return estadoTurnoRepository.findById(id)
                .map(et -> estadoTurnoRepository.save(appointmentStatus))
                .orElse(null);
    }

    @Override
    public AppointmentStatus delete(Long id) {
        return estadoTurnoRepository.findById(id)
                .map(et -> {
                    et.setDeletedAt(LocalDateTime.now());
                    return estadoTurnoRepository.save(et);
                }).orElse(null);
    }


}

