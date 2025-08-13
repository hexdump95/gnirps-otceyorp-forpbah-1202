package com.example.hospital.repositories;

import com.example.hospital.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Appointment, Long> {
}
