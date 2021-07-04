package com.example.hospital.services;

import com.example.hospital.entities.EstadoTurno;
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
    public List<EstadoTurno> findAll(boolean showDeleted) {
        if(showDeleted)
        return estadoTurnoRepository.findAll();
        else return estadoTurnoRepository.findByFechaBajaEstadoTurnoIsNull();
    }

    @Override
    public EstadoTurno findById(Long id) {
        return estadoTurnoRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public EstadoTurno save(EstadoTurno estadoTurno) {
        return estadoTurnoRepository
                .save(estadoTurno);
    }

    @Override
    public EstadoTurno update(Long id, EstadoTurno estadoTurno) {
        return estadoTurnoRepository.findById(id)
                .map(et -> estadoTurnoRepository.save(estadoTurno))
                .orElse(null);
    }

    @Override
    public EstadoTurno delete(Long id) {
        return estadoTurnoRepository.findById(id)
                .map(et -> {
                    et.setFechaBajaEstadoTurno(LocalDateTime.now());
                    return estadoTurnoRepository.save(et);
                }).orElse(null);
    }


}

