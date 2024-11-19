package com.cookease.cook_ease.application.service;



import com.cookease.cook_ease.application.dto.MedallaDTO;

import java.util.List;

public interface MedallaService {
    MedallaDTO crearMedalla(MedallaDTO medallaDTO);
    MedallaDTO obtenerMedallaPorId(Integer idMedalla);
    List<MedallaDTO> listarMedallas();
    MedallaDTO actualizarMedalla(Integer idMedalla, MedallaDTO medallaDTO);
    void eliminarMedalla(Integer idMedalla);
}