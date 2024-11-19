package com.cookease.cook_ease.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idReto;

    @Column(length = 255, nullable = false)
    private String descripcion;

    @Column(length = 255)
    private String imgUrl; // Nueva campo para la URL de la imagen

    @ManyToOne
    @JoinColumn(name = "idMedalla", nullable = false)
    private Medalla medalla;
}
