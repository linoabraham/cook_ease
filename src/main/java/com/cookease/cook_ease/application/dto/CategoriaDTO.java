package com.cookease.cook_ease.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private Integer idCategoria;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Size(max = 100, message = "El nombre de la categoría no puede exceder 100 caracteres")
    private String nombre;

    private Set<Integer> comidas; // IDs de Comidas asociadas
}