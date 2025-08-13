package com.example.hospital.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private LocalDateTime deletedAt;
    private Integer phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultingRoom> consultingRooms = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<ConsultingRoom> getConsultingRooms() {
        return consultingRooms;
    }

    public void setConsultingRooms(List<ConsultingRoom> consultingRooms) {
        this.consultingRooms = consultingRooms;
    }
}
