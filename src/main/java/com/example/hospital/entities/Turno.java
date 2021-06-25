package com.example.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Turno extends BaseEntity{
    private LocalDateTime fechaTurno;
    private Float monto;
    @OneToOne
    private SolicitudTurno solicitudTurno;
    @ManyToOne
    private EstadoTurno estadoTurno;
    @ManyToOne
    private Consultorio consultorio;
    @ManyToOne
    private Persona medico;
}
