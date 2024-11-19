package com.cookease.cook_ease.application.service;



import com.cookease.cook_ease.application.dto.EtiquetaDTO;
import com.cookease.cook_ease.application.dto.EtiquetaDetalleDTO;

import java.util.List;

public interface EtiquetaService {
    EtiquetaDTO crearEtiqueta(EtiquetaDTO etiquetaDTO);
    EtiquetaDTO obtenerEtiquetaPorNombre(String nombre);
    EtiquetaDetalleDTO obtenerEtiquetaDetallePorNombre(String nombre);
    List<EtiquetaDTO> listarEtiquetas();
    EtiquetaDTO actualizarEtiqueta(String nombre, EtiquetaDTO etiquetaDTO);
    void eliminarEtiqueta(String nombre);
}