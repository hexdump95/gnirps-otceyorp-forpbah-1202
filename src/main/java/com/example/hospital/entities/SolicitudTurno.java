package com.example.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitudTurno extends BaseEntity {
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaSolicitudTurno;
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitudEstado> solicitudEstadoList = new ArrayList<>();
    @ManyToOne(optional = false)
    private Especialidad especialidad;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona paciente;
}
