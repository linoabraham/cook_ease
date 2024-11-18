package com.cookease.cook_ease.application.service;



import com.cookease.cook_ease.application.dto.EtiquetaDTO;

import java.util.List;

public interface EtiquetaService {
    EtiquetaDTO crearEtiqueta(EtiquetaDTO etiquetaDTO);
    EtiquetaDTO obtenerEtiquetaPorId(Integer idEtiqueta);
    List<EtiquetaDTO> listarEtiquetas();
    EtiquetaDTO actualizarEtiqueta(Integer idEtiqueta, EtiquetaDTO etiquetaDTO);
    void eliminarEtiqueta(Integer idEtiqueta);
}