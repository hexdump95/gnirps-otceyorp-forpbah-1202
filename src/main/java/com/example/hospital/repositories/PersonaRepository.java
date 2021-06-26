package com.example.hospital.repositories;

import com.example.hospital.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Persona findByUsuarioUsername(String username);
    Persona findByUsuarioId(UUID userId);
}
