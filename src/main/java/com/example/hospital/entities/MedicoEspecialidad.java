package com.example.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicoEspecialidad extends BaseEntity {
    private LocalDateTime fechaDesdeMedicoEspecialidad;
    @ManyToOne
    private Especialidad especialidad;
}
