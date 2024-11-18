package com.cookease.cook_ease.application.dto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComidaDTO {

    private Integer idComida;

    @NotBlank(message = "El nombre de la comida es obligatorio")
    @Size(max = 100, message = "El nombre de la comida no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "La receta es obligatoria")
    @Size(max = 1000, message = "La receta no puede exceder 1000 caracteres")
    private String receta;

    @NotBlank(message = "Las instrucciones son obligatorias")
    @Size(max = 1000, message = "Las instrucciones no pueden exceder 1000 caracteres")
    private String instrucciones;

    @NotBlank(message = "La descripción nutricional es obligatoria")
    @Size(max = 500, message = "La descripción nutricional no puede exceder 500 caracteres")
    private String descripcionNutritional; // Descripción textual de la información nutricional

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria; // Nombre de la categoría

    @NotEmpty(message = "La lista de ingredientes no puede estar vacía")
    private List<@NotBlank(message = "El nombre del ingrediente no puede estar vacío") String> ingredientes; // Nombres de Ingredientes

    @URL(message = "La imagenUrl debe ser una URL válida")
    private String imagenUrl; // URL completa de la imagen externa
}