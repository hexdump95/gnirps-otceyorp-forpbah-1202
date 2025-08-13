package com.example.hospital.dtos;

import com.example.hospital.entities.Specialty;
import com.example.hospital.entities.StatusRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestDetailsDto {
    private Long id;
    private LocalDateTime createdAt;
    private String description;
    private List<StatusRequest> statusRequests = new ArrayList<>();
    private Specialty specialty;

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

    public List<StatusRequest> getStatusRequests() {
        return statusRequests;
    }

    public void setStatusRequests(List<StatusRequest> statusRequests) {
        this.statusRequests = statusRequests;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
