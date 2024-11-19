package com.cookease.cook_ease.application.service.impl;

import com.cookease.cook_ease.application.dto.MedallaDTO;
import com.cookease.cook_ease.application.service.MedallaService;
import com.cookease.cook_ease.domain.model.Etiqueta;
import com.cookease.cook_ease.domain.model.Medalla;

import com.cookease.cook_ease.domain.repository.EtiquetaRepository;
import com.cookease.cook_ease.domain.repository.MedallaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedallaServiceImpl implements MedallaService {

    @Autowired
    private MedallaRepository medallaRepository;

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @Override
    public MedallaDTO crearMedalla(MedallaDTO medallaDTO) {
        Medalla medalla = new Medalla();
        medalla.setNombremedalla(medallaDTO.getNombre());
        medalla.setNivel(medallaDTO.getNivel());
        medalla.setImgUrl(medallaDTO.getImgUrl());

        Etiqueta etiqueta = etiquetaRepository.findById(medallaDTO.getNombreEtiqueta())
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con nombre: " + medallaDTO.getNombreEtiqueta()));

        medalla.setEtiqueta(etiqueta);
        medalla = medallaRepository.save(medalla);
        medallaDTO.setIdMedalla(medalla.getIdMedalla());
        return mapearEntidadADto(medalla);
    }

    @Override
    public MedallaDTO obtenerMedallaPorId(Integer idMedalla) {
        Medalla medalla = medallaRepository.findById(idMedalla)
                .orElseThrow(() -> new NoSuchElementException("Medalla no encontrada con id: " + idMedalla));
        return mapearEntidadADto(medalla);
    }

    @Override
    public List<MedallaDTO> listarMedallas() {
        List<Medalla> medallas = medallaRepository.findAll();
        return medallas.stream().map(this::mapearEntidadADto).collect(Collectors.toList());
    }

    @Override
    public MedallaDTO actualizarMedalla(Integer idMedalla, MedallaDTO medallaDTO) {
        Medalla medalla = medallaRepository.findById(idMedalla)
                .orElseThrow(() -> new NoSuchElementException("Medalla no encontrada con id: " + idMedalla));

        medalla.setNombremedalla(medallaDTO.getNombre());
        medalla.setNivel(medallaDTO.getNivel());
        medalla.setImgUrl(medallaDTO.getImgUrl());

        if (medallaDTO.getNombreEtiqueta() != null) {
            Etiqueta etiqueta = etiquetaRepository.findById(medallaDTO.getNombreEtiqueta())
                    .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con nombre: " + medallaDTO.getNombreEtiqueta()));
            medalla.setEtiqueta(etiqueta);
        }

        medalla = medallaRepository.save(medalla);
        return mapearEntidadADto(medalla);
    }

    @Override
    public void eliminarMedalla(Integer idMedalla) {
        Medalla medalla = medallaRepository.findById(idMedalla)
                .orElseThrow(() -> new NoSuchElementException("Medalla no encontrada con id: " + idMedalla));
        medallaRepository.delete(medalla);
    }

    // Métodos de mapeo

    private MedallaDTO mapearEntidadADto(Medalla medalla) {
        MedallaDTO dto = new MedallaDTO();
        dto.setIdMedalla(medalla.getIdMedalla());
        dto.setNombre(medalla.getNombremedalla());
        dto.setNivel(medalla.getNivel());
        dto.setImgUrl(medalla.getImgUrl());
        dto.setNombreEtiqueta(medalla.getEtiqueta().getNombre());
        return dto;
    }
}