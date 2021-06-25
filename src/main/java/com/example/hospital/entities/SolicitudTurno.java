package com.example.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitudTurno extends BaseEntity {
    private UUID codSolicitud;
    private LocalDateTime fechaSolicitudTurno;
    private String derivacion;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitudEstado> solicitudEstadoList = new ArrayList<>();
    @ManyToOne
    private Especialidad especialidad;
    @ManyToOne
    private Persona paciente;
}
