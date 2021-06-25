package com.example.hospital.entities;

import com.example.hospital.entities.BaseEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Usuario extends BaseEntity {
    @Getter
    private String username;
    @ToString.Exclude @Setter // ...
    private String password;
    @Getter
    private LocalDateTime fechaDesdeUsuario;
    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioRol> usuarioRolList = new ArrayList<>();
}
