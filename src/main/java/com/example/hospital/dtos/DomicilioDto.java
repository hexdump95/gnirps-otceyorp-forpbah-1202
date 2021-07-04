package com.example.hospital.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DomicilioDto {
    private Long id;
    private String calle;
    private Integer nro;
    private EntidadDto localidad;
}
