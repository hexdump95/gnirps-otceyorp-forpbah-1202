package com.example.hospital.dtos;

public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer nationalId;

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

    public Integer getNationalId() {
        return nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }
}
