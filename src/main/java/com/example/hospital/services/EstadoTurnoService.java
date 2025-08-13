package com.example.hospital.services;

import com.example.hospital.entities.AppointmentStatus;

import java.util.List;

public interface EstadoTurnoService {
    List<AppointmentStatus> findAll(boolean showDeleted);
    AppointmentStatus findById(Long id);
    AppointmentStatus save(AppointmentStatus appointmentStatus);
    AppointmentStatus update(Long id, AppointmentStatus appointmentStatus);
    AppointmentStatus delete(Long id);
}
