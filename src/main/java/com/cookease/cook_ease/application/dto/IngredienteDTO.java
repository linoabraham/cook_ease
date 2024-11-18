package com.cookease.cook_ease.application.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredienteDTO {

    private Integer idIngrediente;

    @NotBlank(message = "El nombre del ingrediente es obligatorio")
    @Size(max = 100, message = "El nombre del ingrediente no puede exceder 100 caracteres")
    private String nombre;
}