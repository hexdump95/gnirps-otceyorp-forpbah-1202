package com.example.hospital.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SolicitudTurnoDto {
    private Long id;
    private UUID codSolicitud;
    private String derivacion;
    private EntidadDto especialidad;
    private EntidadDto paciente;
}
