package com.example.hospital.controllers;

import com.example.hospital.entities.Persona;
import com.example.hospital.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Persona persona) {
        String username = authService.register(persona);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("mensaje", "usuario "+username+" creado!"));
    }

}
