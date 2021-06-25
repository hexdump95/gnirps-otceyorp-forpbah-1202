package com.example.hospital.repositories;

import com.example.hospital.entities.SolicitudTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudTurnoRepository extends JpaRepository<SolicitudTurno, Long> {
}
