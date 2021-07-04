package com.example.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioRol extends BaseEntity {
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaDesde;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol {
        ADMIN, MEDICO, RECEPCIONISTA, SOCIO
    }
}
