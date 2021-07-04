package com.example.hospital.repositories;

import com.example.hospital.entities.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoSolicitudRepository extends JpaRepository<EstadoSolicitud, Long> {
    List<EstadoSolicitud> findByFechaBajaEstadoSolicitudIsNull();
    EstadoSolicitud findByNombreEstadoSolicitud(String nombre);
}
