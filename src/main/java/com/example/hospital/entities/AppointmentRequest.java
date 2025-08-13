package com.example.hospital.entities;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AppointmentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatusRequest> statusRequests = new ArrayList<>();
    @ManyToOne(optional = false)
    private Specialty specialty;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Person patient;

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

    public Person getPatient() {
        return patient;
    }

    public void setPatient(Person patient) {
        this.patient = patient;
    }
}
