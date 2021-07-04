package com.example.hospital.services;

import com.example.hospital.dtos.RegistrarPersonaDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    String register(RegistrarPersonaDto persona);
}
