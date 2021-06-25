package com.example.hospital.repositories;

import com.example.hospital.entities.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoSolicitudRepository extends JpaRepository<EstadoSolicitud, Long> {
    EstadoSolicitud findByNombreEstadoSolicitud(String nombre);
}
