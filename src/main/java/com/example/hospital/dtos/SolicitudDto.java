package com.example.hospital.dtos;

import com.example.hospital.entities.Especialidad;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SolicitudDto {
    private Long id;
    private LocalDateTime fechaSolicitudTurno;
    private String descripcion;
    //    private List<SolicitudEstado> solicitudEstadoList = new ArrayList<>();
    private Especialidad especialidad;
    private PersonaDto paciente;
}
