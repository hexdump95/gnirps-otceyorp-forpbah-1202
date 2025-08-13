package com.example.hospital.repositories;

import com.example.hospital.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonaRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUserUsername(String username);

    Person findByUserId(UUID userId);

    @Query("SELECT p from Person AS p " +
            "JOIN FETCH p.user AS u " +
            "LEFT JOIN p.doctorSpecialties AS ds " +
            "LEFT JOIN ds.specialty AS e " +
            "WHERE e.id = :especialidadId ")
    List<Person> findAllBySpecialtyId(Long especialidadId);

//    @Query(value = "SELECT p FROM Person AS p " +
//            "JOIN FETCH p.user AS u " +
//            "WHERE u.createdAt < :time " +
//            "AND u.userRoles.size = 0"
//    )
//    List<Person> findAllByUserCreatedAtIsLessThan60DaysAndUserRoleIsNull(LocalDateTime time);
}
