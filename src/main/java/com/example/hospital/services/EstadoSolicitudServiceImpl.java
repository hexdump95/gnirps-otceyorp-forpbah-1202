package com.example.hospital.services;

import com.example.hospital.entities.EstadoSolicitud;
import com.example.hospital.repositories.EstadoSolicitudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstadoSolicitudServiceImpl implements EstadoSolicitudService {
    private final EstadoSolicitudRepository estadoSolicitudRepository;

    public EstadoSolicitudServiceImpl(EstadoSolicitudRepository estadoSolicitudRepository) {
        this.estadoSolicitudRepository = estadoSolicitudRepository;
    }

    @Override
    public List<EstadoSolicitud> findAll(boolean showDeleted) {
        if(showDeleted)
            return estadoSolicitudRepository.findAll();
        else return estadoSolicitudRepository.findByFechaBajaEstadoSolicitudIsNull();
    }

    @Override
    public EstadoSolicitud findById(Long id) {
        return estadoSolicitudRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public EstadoSolicitud save(EstadoSolicitud estadoSolicitud) {
        return estadoSolicitudRepository
                .save(estadoSolicitud);
    }

    @Override
    public EstadoSolicitud update(Long id, EstadoSolicitud estadoSolicitud) {
        return estadoSolicitudRepository.findById(id)
                .map(es -> estadoSolicitudRepository.save(estadoSolicitud))
                .orElse(null);
    }

    @Override
    public EstadoSolicitud delete(Long id) {
        return estadoSolicitudRepository.findById(id)
                .map(es -> {
                    es.setFechaBajaEstadoSolicitud(LocalDateTime.now());
                    return estadoSolicitudRepository.save(es);
                }).orElse(null);
    }


}
