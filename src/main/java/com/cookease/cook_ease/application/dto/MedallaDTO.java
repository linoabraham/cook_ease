package com.cookease.cook_ease.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedallaDTO {
    private Integer idMedalla;
    private String nombre;
    private String nivel;
    private Integer idEtiqueta;
}