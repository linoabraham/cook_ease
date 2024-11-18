package com.cookease.cook_ease.application.service.impl;
import com.cookease.cook_ease.application.dto.EtiquetaDTO;
import com.cookease.cook_ease.application.service.EtiquetaService;
import com.cookease.cook_ease.domain.model.Etiqueta;
import com.cookease.cook_ease.domain.repository.EtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EtiquetaServiceImpl implements EtiquetaService {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @Override
    public EtiquetaDTO crearEtiqueta(EtiquetaDTO etiquetaDTO) {
        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setNombre(etiquetaDTO.getNombre());
        etiqueta = etiquetaRepository.save(etiqueta);
        etiquetaDTO.setIdEtiqueta(etiqueta.getIdEtiqueta());
        return etiquetaDTO;
    }

    @Override
    public EtiquetaDTO obtenerEtiquetaPorId(Integer idEtiqueta) {
        Etiqueta etiqueta = etiquetaRepository.findById(idEtiqueta)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con id: " + idEtiqueta));
        EtiquetaDTO dto = mapearEntidadADto(etiqueta);
        return dto;
    }

    @Override
    public List<EtiquetaDTO> listarEtiquetas() {
        List<Etiqueta> etiquetas = etiquetaRepository.findAll();
        return etiquetas.stream().map(this::mapearEntidadADto).collect(Collectors.toList());
    }

    @Override
    public EtiquetaDTO actualizarEtiqueta(Integer idEtiqueta, EtiquetaDTO etiquetaDTO) {
        Etiqueta etiqueta = etiquetaRepository.findById(idEtiqueta)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con id: " + idEtiqueta));

        etiqueta.setNombre(etiquetaDTO.getNombre());
        etiqueta = etiquetaRepository.save(etiqueta);
        return mapearEntidadADto(etiqueta);
    }

    @Override
    public void eliminarEtiqueta(Integer idEtiqueta) {
        Etiqueta etiqueta = etiquetaRepository.findById(idEtiqueta)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con id: " + idEtiqueta));
        etiquetaRepository.delete(etiqueta);
    }

    // Métodos de mapeo

    private EtiquetaDTO mapearEntidadADto(Etiqueta etiqueta) {
        EtiquetaDTO dto = new EtiquetaDTO();
        dto.setIdEtiqueta(etiqueta.getIdEtiqueta());
        dto.setNombre(etiqueta.getNombre());
        return dto;
    }
}