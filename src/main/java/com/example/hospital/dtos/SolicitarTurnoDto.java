package com.example.hospital.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SolicitarTurnoDto {
    private EntidadDto especialidad;
    private DomicilioDto domicilioDto;
}
