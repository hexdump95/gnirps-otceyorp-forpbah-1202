package com.example.hospital.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer phoneNumber;
    private Integer nationalId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoctorSpecialty> doctorSpecialties = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getNationalId() {
        return nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<DoctorSpecialty> getDoctorSpecialties() {
        return doctorSpecialties;
    }

    public void setDoctorSpecialties(List<DoctorSpecialty> doctorSpecialties) {
        this.doctorSpecialties = doctorSpecialties;
    }
}
