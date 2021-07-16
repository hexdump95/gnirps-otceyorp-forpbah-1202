package com.example.hospital.controllers;

import com.example.hospital.Routes;
import com.example.hospital.dtos.SignupUsuarioDto;
import com.example.hospital.dtos.LoginUsuarioDto;
import com.example.hospital.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@Tag(name = "Auth")
@RequestMapping
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Registrar usuario")
    @PostMapping(Routes.REGISTER_URL)
    public ResponseEntity<Map<String, String>> register(@RequestBody SignupUsuarioDto usuarioDto) {
        String username = authService.register(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("mensaje", "usuario "+username+" creado!"));
    }

    @Operation(summary = "Login usuario")
    @PostMapping(Routes.LOGIN_URL)
    public void login(@RequestBody LoginUsuarioDto loginDto) {
        throw new UnsupportedOperationException();
    }

}
