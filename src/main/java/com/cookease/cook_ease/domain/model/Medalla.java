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
public class Medalla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idMedalla;

    @Column(length = 100, nullable = false)
    private String nombremedalla;

    @Column(length = 50, nullable = false)
    private String nivel;

    @Column(length = 255)
    private String imgUrl; // Nueva campo para la URL de la imagen

    @ManyToOne
    @JoinColumn(name = "nombre", nullable = false) // Usamos nombre de Etiqueta
    private Etiqueta etiqueta;

    @OneToMany(mappedBy = "medalla", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reto> retos = new HashSet<>();
}