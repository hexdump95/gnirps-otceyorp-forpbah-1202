package com.example.hospital.services;

import com.example.hospital.entities.RequestStatus;
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
    public List<RequestStatus> findAll(boolean showDeleted) {
        if (showDeleted)
            return estadoSolicitudRepository.findAll();
        else return estadoSolicitudRepository.findByDeletedAtIsNull();
    }

    @Override
    public RequestStatus findById(Long id) {
        return estadoSolicitudRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public RequestStatus save(RequestStatus requestStatus) {
        return estadoSolicitudRepository
                .save(requestStatus);
    }

    @Override
    public RequestStatus update(Long id, RequestStatus requestStatus) {
        return estadoSolicitudRepository.findById(id)
                .map(es -> estadoSolicitudRepository.save(requestStatus))
                .orElse(null);
    }

    @Override
    public RequestStatus delete(Long id) {
        return estadoSolicitudRepository.findById(id)
                .map(es -> {
                    es.setDeletedAt(LocalDateTime.now());
                    return estadoSolicitudRepository.save(es);
                }).orElse(null);
    }

}
