package com.example.hospital.repositories;

import com.example.hospital.entities.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoSolicitudRepository extends JpaRepository<RequestStatus, Long> {
    List<RequestStatus> findByDeletedAtIsNull();
    RequestStatus findByName(String name);
}
