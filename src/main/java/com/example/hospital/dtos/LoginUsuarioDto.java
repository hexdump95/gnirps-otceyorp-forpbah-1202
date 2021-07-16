package com.example.hospital.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUsuarioDto {
    private String username;
    private String password;
}
