package com.example.hospital.dtos;

import com.example.hospital.entities.Specialty;

import java.time.LocalDateTime;

public class RequestDto {
    private Long id;
    private LocalDateTime createdAt;
    private String description;
    private Specialty specialty;
    private PersonDto patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public PersonDto getPatient() {
        return patient;
    }

    public void setPatient(PersonDto patient) {
        this.patient = patient;
    }
}
