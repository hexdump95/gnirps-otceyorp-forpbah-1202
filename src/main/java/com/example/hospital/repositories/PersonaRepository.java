package com.example.hospital.repositories;

import com.example.hospital.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    @Query("SELECT p from Persona AS p " +
            "JOIN FETCH p.usuario AS u " +
            "WHERE u.username = :username ")
    Optional<Persona> findByUsuarioUsername(String username);

    @Query("SELECT p from Persona AS p " +
            "JOIN FETCH p.usuario AS u " +
            "WHERE u.id = :userId ")
    Persona findByUsuarioId(UUID userId);

    @Query("SELECT p from Persona AS p " +
            "JOIN FETCH p.usuario AS u " +
            "LEFT JOIN p.medicoEspecialidadList AS mel " +
            "LEFT JOIN mel.especialidad AS e " +
            "WHERE e.id = :especialidadId ")
    List<Persona> findAllByMedicoEspecialidadId(Long especialidadId);

    @Query(value = "SELECT p FROM Persona AS p " +
            "JOIN FETCH p.usuario AS u " +
            "WHERE u.fechaDesdeUsuario < :time " +
            "AND u.usuarioRolList.size = 0"
    )
    List<Persona> findAllByFechaDesdeUsuarioLessThan60DaysAndUsuarioRolIsNull(LocalDateTime time);
}
