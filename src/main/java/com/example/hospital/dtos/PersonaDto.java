package com.example.hospital.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonaDto {
    private Long id;
    private String nombrePersona;
    private String apellidoPersona;
    private Integer dniPersona;
}
