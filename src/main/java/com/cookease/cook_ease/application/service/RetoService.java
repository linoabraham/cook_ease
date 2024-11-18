package com.cookease.cook_ease.application.service;

import com.cookease.cook_ease.application.dto.RetoDTO;

import java.util.List;

public interface RetoService {
    RetoDTO crearReto(RetoDTO retoDTO);
    RetoDTO obtenerRetoPorId(Integer idReto);
    List<RetoDTO> listarRetos();
    RetoDTO actualizarReto(Integer idReto, RetoDTO retoDTO);
    void eliminarReto(Integer idReto);
}