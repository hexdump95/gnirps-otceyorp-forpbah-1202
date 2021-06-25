package com.example.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitudTurno extends BaseEntity {
    private UUID codSolicitud;
    private LocalDate fechaSolicitudTurno;
    private String derivacion;
}
