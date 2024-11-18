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
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idComida;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 1000, nullable = false)
    private String receta;

    @Column(length = 1000, nullable = false)
    private String instrucciones;

    @Column(length = 500, nullable = false)
    private String descripcionNutritional; // Descripción textual de la información nutricional

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
            name = "comida_ingrediente",
            joinColumns = @JoinColumn(name = "idComida"),
            inverseJoinColumns = @JoinColumn(name = "idIngrediente")
    )
    private Set<Ingrediente> ingredientes = new HashSet<>();

    @Column(length = 2083) // Longitud estándar máxima para URLs
    private String imagenUrl; // URL completa de la imagen externa
}