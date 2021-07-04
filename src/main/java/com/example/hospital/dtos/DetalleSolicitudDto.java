package com.example.hospital.dtos;

import com.example.hospital.entities.Especialidad;
import com.example.hospital.entities.SolicitudEstado;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DetalleSolicitudDto {
    private Long id;
    private LocalDateTime fechaSolicitudTurno;
    private String descripcion;
    private List<SolicitudEstado> solicitudEstadoList = new ArrayList<>();
    private Especialidad especialidad;
}
