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
public class Persona extends BaseEntity {
    private String nombrePersona;
    private String apellidoPersona;
    private Integer telefonoPersona;
    private Integer dniPersona;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usuario usuario;
    @OneToOne(cascade = CascadeType.ALL)
    private Domicilio domicilio;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicoEspecialidad> medicoEspecialidadList = new ArrayList<>();
}
