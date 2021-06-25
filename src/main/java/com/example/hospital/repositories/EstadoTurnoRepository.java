package com.example.hospital.repositories;

import com.example.hospital.entities.EstadoTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoTurnoRepository extends JpaRepository<EstadoTurno, Long> {
    EstadoTurno findByNombreEstadoTurno(String nombreEstadoTurno);
}
