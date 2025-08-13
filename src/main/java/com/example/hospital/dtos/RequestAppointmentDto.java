package com.example.hospital.dtos;

public class RequestAppointmentDto {
    private EntityDto specialty;
    private AddressDto addressDto;

    public EntityDto getSpecialty() {
        return specialty;
    }

    public void setSpecialty(EntityDto specialty) {
        this.specialty = specialty;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }
}
