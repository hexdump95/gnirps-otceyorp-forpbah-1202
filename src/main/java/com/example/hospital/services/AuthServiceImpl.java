package com.example.hospital.services;

import com.example.hospital.dtos.RegistrarPersonaDto;
import com.example.hospital.entities.Persona;
import com.example.hospital.entities.UsuarioRol;
import com.example.hospital.repositories.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public AuthServiceImpl(PersonaRepository personaRepository,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper) {
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Scheduled(cron = "0 0 0 0/1 * *")
    public void deleteUser() {
        LocalDateTime time = LocalDateTime.now().minusDays(60);
        List<Persona> personas = personaRepository
                .findAllByFechaDesdeUsuarioLessThan60DaysAndUsuarioRolIsNull(time);
        personaRepository.deleteAll(personas);
    }

    @Override
    public String register(RegistrarPersonaDto personaDto) {
        Persona persona = modelMapper.map(personaDto, Persona.class);
        persona.getUsuario().setId(UUID.randomUUID());
        persona.getUsuario().setPassword(passwordEncoder.encode(persona.getUsuario().getPassword()));
        personaRepository.save(persona);
        return persona.getUsuario().getUsername();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personaRepository.findByUsuarioUsername(username)
                .map(p -> {
                    String userId = p.getUsuario().getId().toString();
                    String password = p.getUsuario().getPassword();
                    List<GrantedAuthority> authorities = p.getUsuario().getUsuarioRolList()
                            .stream().map(UsuarioRol::getRol)
                            .map(r -> "ROLE_" + r.toString())
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    return new User(userId, password, authorities);
                }).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
