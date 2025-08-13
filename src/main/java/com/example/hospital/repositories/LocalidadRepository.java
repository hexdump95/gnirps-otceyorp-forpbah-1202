package com.example.hospital.repositories;

import com.example.hospital.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalidadRepository extends JpaRepository<District, Long> {
    List<District> findByDeletedAtIsNull();
}
