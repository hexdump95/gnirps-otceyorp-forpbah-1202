package com.example.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hospital extends BaseEntity {
    private String nombreHospital;
    private String fechaBajaHospital;
    private Integer telefonoHospital;
    @OneToOne(cascade = CascadeType.ALL)
    private Domicilio domicilio;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consultorio> consultorioList = new ArrayList<>();
}
