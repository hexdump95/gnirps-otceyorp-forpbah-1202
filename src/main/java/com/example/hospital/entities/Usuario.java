package com.example.hospital.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    @Id private UUID id;
    @Column(unique = true)
    private String username;
    private String password;
    private LocalDateTime fechaDesdeUsuario;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioRol> usuarioRolList = new ArrayList<>();
}
