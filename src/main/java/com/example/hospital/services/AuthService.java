package com.example.hospital.services;

import com.example.hospital.entities.Persona;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    String register(Persona persona);
}
