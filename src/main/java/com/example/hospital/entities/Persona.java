package com.example.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Persona extends BaseEntity {
    private String nombrePersona;
    private String apellidoPersona;
    private Integer telefonoPersona;
    private Integer dniPersona;
    private String emailPersona;
    //private String userId;
}
