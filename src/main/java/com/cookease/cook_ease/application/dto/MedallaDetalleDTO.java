package com.cookease.cook_ease.application.dto;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedallaDetalleDTO {
    private Integer idMedalla;
    private String nombre;
    private String nivel;
    private String imgUrl;
    private Set<RetoDTO> retos;
}