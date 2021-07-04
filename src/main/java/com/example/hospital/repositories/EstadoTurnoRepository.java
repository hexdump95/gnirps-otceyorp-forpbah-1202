package com.example.hospital.repositories;

import com.example.hospital.entities.EstadoTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoTurnoRepository extends JpaRepository<EstadoTurno, Long> {
    List<EstadoTurno> findByFechaBajaEstadoTurnoIsNull();
    EstadoTurno findByNombreEstadoTurno(String nombreEstadoTurno);
}
