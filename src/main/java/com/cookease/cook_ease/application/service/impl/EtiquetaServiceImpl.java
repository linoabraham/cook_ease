package com.cookease.cook_ease.application.service.impl;
import com.cookease.cook_ease.application.dto.EtiquetaDTO;
import com.cookease.cook_ease.application.dto.EtiquetaDetalleDTO;
import com.cookease.cook_ease.application.dto.MedallaDetalleDTO;
import com.cookease.cook_ease.application.dto.RetoDTO;
import com.cookease.cook_ease.application.service.EtiquetaService;
import com.cookease.cook_ease.domain.model.Etiqueta;
import com.cookease.cook_ease.domain.model.Reto;
import com.cookease.cook_ease.domain.repository.EtiquetaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EtiquetaServiceImpl implements EtiquetaService {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @Override
    public EtiquetaDTO crearEtiqueta(EtiquetaDTO etiquetaDTO) {
        if (etiquetaRepository.existsById(etiquetaDTO.getNombre())) {
            throw new IllegalArgumentException("Etiqueta con nombre '" + etiquetaDTO.getNombre() + "' ya existe.");
        }
        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setNombre(etiquetaDTO.getNombre());
        etiqueta = etiquetaRepository.save(etiqueta);
        return mapearEntidadADto(etiqueta);
    }

    @Override
    public EtiquetaDTO obtenerEtiquetaPorNombre(String nombre) {
        Etiqueta etiqueta = etiquetaRepository.findById(nombre)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con nombre: " + nombre));
        return mapearEntidadADto(etiqueta);
    }

    @Override
    public EtiquetaDetalleDTO obtenerEtiquetaDetallePorNombre(String nombre) {
        Etiqueta etiqueta = etiquetaRepository.findById(nombre)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con nombre: " + nombre));

        Set<MedallaDetalleDTO> medallasDTO = etiqueta.getMedallas().stream().map(medalla -> {
            Set<RetoDTO> retosDTO = medalla.getRetos().stream()
                    .map(this::mapearRetoAtoDTO)
                    .collect(Collectors.toSet());
            return new MedallaDetalleDTO(
                    medalla.getIdMedalla(),
                    medalla.getNombremedalla(),
                    medalla.getNivel(),
                    medalla.getImgUrl(),
                    retosDTO
            );
        }).collect(Collectors.toSet());

        return new EtiquetaDetalleDTO(
                etiqueta.getNombre(),
                medallasDTO
        );
    }

    @Override
    public List<EtiquetaDTO> listarEtiquetas() {
        List<Etiqueta> etiquetas = etiquetaRepository.findAll();
        return etiquetas.stream().map(this::mapearEntidadADto).collect(Collectors.toList());
    }

    @Override
    public EtiquetaDTO actualizarEtiqueta(String nombre, EtiquetaDTO etiquetaDTO) {
        Etiqueta etiqueta = etiquetaRepository.findById(nombre)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con nombre: " + nombre));
        etiqueta.setNombre(etiquetaDTO.getNombre());
        etiqueta = etiquetaRepository.save(etiqueta);
        return mapearEntidadADto(etiqueta);
    }

    @Override
    public void eliminarEtiqueta(String nombre) {
        Etiqueta etiqueta = etiquetaRepository.findById(nombre)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con nombre: " + nombre));
        etiquetaRepository.delete(etiqueta);
    }

    // Métodos de mapeo

    private EtiquetaDTO mapearEntidadADto(Etiqueta etiqueta) {
        EtiquetaDTO dto = new EtiquetaDTO();
        dto.setNombre(etiqueta.getNombre());
        return dto;
    }

    private RetoDTO mapearRetoAtoDTO(Reto reto) {
        RetoDTO dto = new RetoDTO();
        dto.setIdReto(reto.getIdReto());
        dto.setDescripcion(reto.getDescripcion());
        dto.setImgUrl(reto.getImgUrl());
        dto.setIdMedalla(reto.getMedalla().getIdMedalla());
        return dto;
    }
}