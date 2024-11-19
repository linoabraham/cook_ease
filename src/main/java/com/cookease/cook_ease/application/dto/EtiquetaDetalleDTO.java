package com.cookease.cook_ease.application.dto;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtiquetaDetalleDTO {
    private String nombre;
    private Set<MedallaDetalleDTO> medallas;
}