package com.example.hospital.services;

import com.example.hospital.entities.Persona;
import com.example.hospital.repositories.PersonaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(PersonaRepository personaRepository,
                           PasswordEncoder passwordEncoder) {
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String register(Persona persona) {
        persona.getUsuario().setId(UUID.randomUUID());
        persona.getUsuario().setPassword(passwordEncoder.encode(persona.getUsuario().getPassword()));
        personaRepository.save(persona);
        return persona.getUsuario().getUsername();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Persona persona = personaRepository.findByUsuarioUsername(username);
        if(persona == null) throw new UsernameNotFoundException(username);

        return new User(username, persona.getUsuario().getPassword(),
                true, true, true,
        true, new ArrayList<>());
    }

}
