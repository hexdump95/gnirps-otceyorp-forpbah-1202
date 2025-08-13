package com.example.hospital.repositories;

import com.example.hospital.entities.AppointmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SolicitudTurnoRepository extends JpaRepository<AppointmentRequest, Long> {

    @Query("SELECT st FROM AppointmentRequest AS st " +
            "JOIN FETCH st.patient AS p " +
            "LEFT JOIN st.statusRequests AS se " +
            "ON se.toDate IS NULL " +
            "LEFT JOIN se.requestStatus AS es " +
            "WHERE es.name = 'Pendiente de Aprobación' ")
    List<AppointmentRequest> findAllByStatusPendiente();

    @Query("SELECT st FROM AppointmentRequest  AS st " +
            "JOIN FETCH st.patient AS p " +
            "WHERE p.user.id = :userId")
    List<AppointmentRequest> findAllByPatientUserId(UUID userId);

    @Query("SELECT st FROM AppointmentRequest  AS st " +
            "JOIN FETCH st.patient AS p " +
            "WHERE p.user.id = :userId " +
            "AND st.id = :id")
    Optional<AppointmentRequest> findByIdAndPatientUserId(Long id, UUID userId);
}
