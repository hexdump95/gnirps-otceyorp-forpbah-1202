package com.example.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    @Id
    private UUID id;
    @Column(unique = true)
    private String username;
    private String password;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaDesdeUsuario;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UsuarioRol> usuarioRolList = new ArrayList<>();
}
