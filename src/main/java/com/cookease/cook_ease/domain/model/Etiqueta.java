package com.cookease.cook_ease.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Etiqueta {

    @Id
    @Column(length = 100)
    @EqualsAndHashCode.Include
    private String nombre; // Usamos el nombre como identificador principal

    @OneToMany(mappedBy = "etiqueta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Medalla> medallas = new HashSet<>();

    @ManyToMany(mappedBy = "etiquetas")
    private Set<Usuario> usuarios = new HashSet<>();
}