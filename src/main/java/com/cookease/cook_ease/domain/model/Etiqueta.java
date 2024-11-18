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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idEtiqueta;

    @Column(length = 100, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "etiqueta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Medalla> medallas = new HashSet<>();

    @ManyToMany(mappedBy = "etiquetas")
    private Set<Usuario> usuarios = new HashSet<>();
}
