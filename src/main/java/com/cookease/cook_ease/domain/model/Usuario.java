package com.cookease.cook_ease.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idUsuario;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String apellido;

    @Column(length = 20, nullable = false)
    private String gender; // "Male", "Female", etc.

    @Column(nullable = false)
    private Integer age;

    @Column(precision = 4, scale = 2, nullable = false)
    private BigDecimal height; // En metros, ej. 1.10

    @Column(nullable = false)
    private Integer weight; // En kilogramos

    @Column(length = 10)
    private String family_history_with_overweight; // "yes", "no"

    @Column(length = 10)
    private String FAVC; // "yes", "no"

    private Integer FCVC;

    private Integer NCP;

    @Column(length = 20)
    private String CAEC; // "Sometimes", etc.

    @Column(length = 10)
    private String SMOKE; // "yes", "no"

    @Column(precision = 3, scale = 1)
    private BigDecimal CH2O;

    @Column(length = 10)
    private String SCC; // "yes", "no"

    private Integer FAF;

    private Integer TUE;

    @Column(length = 10)
    private String CALC; // "yes", "no"

    @Column(length = 20)
    private String MTRANS; // "Walking", etc.

    @ManyToMany
    @JoinTable(
            name = "usuario_etiqueta",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "nombre") // Usamos nombre en vez de id
    )
    private Set<Etiqueta> etiquetas = new HashSet<>();
}