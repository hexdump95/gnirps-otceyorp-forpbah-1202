package com.example.hospital.repositories;

import com.example.hospital.entities.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoTurnoRepository extends JpaRepository<AppointmentStatus, Long> {
    List<AppointmentStatus> findByDeletedAtIsNull();
    AppointmentStatus findByName(String name);
}
