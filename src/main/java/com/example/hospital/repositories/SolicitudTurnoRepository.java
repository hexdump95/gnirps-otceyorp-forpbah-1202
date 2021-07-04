package com.example.hospital.repositories;

import com.example.hospital.entities.SolicitudTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SolicitudTurnoRepository extends JpaRepository<SolicitudTurno, Long> {

    @Query("SELECT st FROM SolicitudTurno AS st " +
            "JOIN FETCH st.paciente AS p " +
            "LEFT JOIN st.solicitudEstadoList AS se " +
            "ON se.fechaHastaSolicitudEstado IS NULL " +
            "LEFT JOIN se.estadoSolicitud AS es " +
            "WHERE es.nombreEstadoSolicitud = 'Pendiente de Aprobación' ")
    List<SolicitudTurno> findAllByEstadoSolicitudPendiente();

    @Query("SELECT st FROM SolicitudTurno  AS st " +
            "JOIN FETCH st.paciente AS p " +
            "WHERE p.usuario.id = :userId")
    List<SolicitudTurno> findAllByPacienteUsuarioId(UUID userId);

    @Query("SELECT st FROM SolicitudTurno  AS st " +
            "JOIN FETCH st.paciente AS p " +
            "WHERE p.usuario.id = :userId " +
            "AND st.id = :id")
    Optional<SolicitudTurno> findByIdAndPacienteUsuarioId(Long id, UUID userId);
}
