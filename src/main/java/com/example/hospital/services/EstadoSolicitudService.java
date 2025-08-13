package com.example.hospital.services;

import com.example.hospital.entities.RequestStatus;

import java.util.List;

public interface EstadoSolicitudService {
    List<RequestStatus> findAll(boolean showDeleted);
    RequestStatus findById(Long id);
    RequestStatus save(RequestStatus requestStatus);
    RequestStatus update(Long id, RequestStatus requestStatus);
    RequestStatus delete(Long id);
}
