package com.example.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstadoSolicitud extends BaseEntity {
    private String nombreEstadoSolicitud;
    private LocalDateTime fechaBajaEstadoSolicitud;
}
