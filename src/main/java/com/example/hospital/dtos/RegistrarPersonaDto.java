package com.example.hospital.dtos;

import com.example.hospital.entities.Domicilio;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrarPersonaDto {
    private String nombrePersona;
    private String apellidoPersona;
    private Integer telefonoPersona;
    private Integer dniPersona;
    private String emailPersona;
    private UsuarioDto usuario;
    private Domicilio domicilio;
}
