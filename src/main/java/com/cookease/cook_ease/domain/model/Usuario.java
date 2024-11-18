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

    @Column(length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column(length = 100)
    private String gender;

    @Column(length = 100)
    private String birthDate;

    @Column(length = 100, unique = true)
    private String email;

    @Column(precision = 5, scale = 2)
    private BigDecimal height;

    @Column(precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(length = 80)
    private String activityLevel;

    private String fitnessGoals;

    private String allergies;

    @Column(precision = 3, scale = 1)
    private BigDecimal sleepHours;

    private Boolean fixedMealSchedule;

    @Column(precision = 3, scale = 1)
    private BigDecimal mealPreparationTime;

    private Integer outOfHomeMealsFrequency;

    @Column(length = 50)
    private String accessToHealthyStores;

    @Column(length = 100)
    private String preferredProtein;

    @Column(length = 50)
    private String fruitConsumptionFrequency;

    @ManyToMany
    @JoinTable(
            name = "usuario_etiqueta",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idEtiqueta")
    )
    private Set<Etiqueta> etiquetas = new HashSet<>();
}
