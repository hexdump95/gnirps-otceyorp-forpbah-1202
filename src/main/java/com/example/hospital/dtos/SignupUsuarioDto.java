package com.example.hospital.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupUsuarioDto {
    private String username;
    private String password;
    private String email;
}
