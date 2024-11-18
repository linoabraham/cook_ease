package com.cookease.cook_ease.application.service.impl;

import com.cookease.cook_ease.application.dto.RetoDTO;
import com.cookease.cook_ease.application.service.RetoService;
import com.cookease.cook_ease.domain.model.Medalla;
import com.cookease.cook_ease.domain.model.Reto;
import com.cookease.cook_ease.domain.repository.MedallaRepository;
import com.cookease.cook_ease.domain.repository.RetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RetoServiceImpl implements RetoService {

    @Autowired
    private RetoRepository retoRepository;

    @Autowired
    private MedallaRepository medallaRepository;

    @Override
    public RetoDTO crearReto(RetoDTO retoDTO) {
        Reto reto = new Reto();
        reto.setDescripcion(retoDTO.getDescripcion());

        Medalla medalla = medallaRepository.findById(retoDTO.getIdMedalla())
                .orElseThrow(() -> new NoSuchElementException("Medalla no encontrada con id: " + retoDTO.getIdMedalla()));

        reto.setMedalla(medalla);
        reto = retoRepository.save(reto);
        retoDTO.setIdReto(reto.getIdReto());
        return retoDTO;
    }

    @Override
    public RetoDTO obtenerRetoPorId(Integer idReto) {
        Reto reto = retoRepository.findById(idReto)
                .orElseThrow(() -> new NoSuchElementException("Reto no encontrado con id: " + idReto));
        return mapearEntidadADto(reto);
    }

    @Override
    public List<RetoDTO> listarRetos() {
        List<Reto> retos = retoRepository.findAll();
        return retos.stream().map(this::mapearEntidadADto).collect(Collectors.toList());
    }

    @Override
    public RetoDTO actualizarReto(Integer idReto, RetoDTO retoDTO) {
        Reto reto = retoRepository.findById(idReto)
                .orElseThrow(() -> new NoSuchElementException("Reto no encontrado con id: " + idReto));

        reto.setDescripcion(retoDTO.getDescripcion());

        if (retoDTO.getIdMedalla() != null) {
            Medalla medalla = medallaRepository.findById(retoDTO.getIdMedalla())
                    .orElseThrow(() -> new NoSuchElementException("Medalla no encontrada con id: " + retoDTO.getIdMedalla()));
            reto.setMedalla(medalla);
        }

        reto = retoRepository.save(reto);
        return mapearEntidadADto(reto);
    }

    @Override
    public void eliminarReto(Integer idReto) {
        Reto reto = retoRepository.findById(idReto)
                .orElseThrow(() -> new NoSuchElementException("Reto no encontrado con id: " + idReto));
        retoRepository.delete(reto);
    }

    // Métodos de mapeo

    private RetoDTO mapearEntidadADto(Reto reto) {
        RetoDTO dto = new RetoDTO();
        dto.setIdReto(reto.getIdReto());
        dto.setDescripcion(reto.getDescripcion());
        dto.setIdMedalla(reto.getMedalla().getIdMedalla());
        return dto;
    }
}