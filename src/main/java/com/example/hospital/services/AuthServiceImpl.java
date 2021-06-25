package com.example.hospital.services;

import com.example.hospital.entities.Persona;
import com.example.hospital.entities.Usuario;
import com.example.hospital.repositories.PersonaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthServiceImpl implements UserDetailsService {
    private final PersonaRepository personaRepository;

    public AuthServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
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
