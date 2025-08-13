package com.example.hospital.services;

import com.example.hospital.entities.Person;
import com.example.hospital.repositories.PersonaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private final PersonaRepository personaRepository;

    public AuthServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

//    @Scheduled(cron = "0 0 1 * * *")
//    public void deleteUser() {
//        LocalDateTime time = LocalDateTime.now().minusDays(60);
//        List<Person> personas = personaRepository
//                .findAllByUserCreatedAtIsLessThan60DaysAndUserRoleIsNull(time);
//        personaRepository.deleteAll(personas);
//    }

}
