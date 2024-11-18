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
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idIngrediente;

    @Column(length = 100, nullable = false, unique = true)
    private String nombre;

    @ManyToMany(mappedBy = "ingredientes")
    private Set<Comida> comidas = new HashSet<>();
}