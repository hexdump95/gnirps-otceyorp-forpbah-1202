package com.example.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitudEstado extends BaseEntity {
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaDesdeSolicitudEstado;
    private LocalDateTime fechaHastaSolicitudEstado;
    @ManyToOne
    private EstadoSolicitud estadoSolicitud;
}
