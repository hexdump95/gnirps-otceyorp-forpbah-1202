package com.example.hospital.services;

import com.example.hospital.dtos.SignupUsuarioDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    String register(SignupUsuarioDto usuarioDto);
}
