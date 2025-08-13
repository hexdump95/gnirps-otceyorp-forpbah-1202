package com.example.hospital.dtos;

public class AddressDto {
    private Long id;
    private String street;
    private Integer number;
    private EntityDto district;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public EntityDto getDistrict() {
        return district;
    }

    public void setDistrict(EntityDto district) {
        this.district = district;
    }
}
