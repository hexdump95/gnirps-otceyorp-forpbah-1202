package com.example.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioRol extends BaseEntity {
    private LocalDateTime fechaDesde;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol {
        ADMIN, MEDICO, SOCIO
    }
}
