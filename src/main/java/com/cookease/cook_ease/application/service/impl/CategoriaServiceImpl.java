package com.cookease.cook_ease.application.service.impl;

import com.cookease.cook_ease.application.dto.CategoriaDTO;
import com.cookease.cook_ease.application.service.CategoriaService;
import com.cookease.cook_ease.domain.model.Categoria;

import com.cookease.cook_ease.domain.repository.CategoriaRepository;
import com.cookease.cook_ease.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.stream.Collectors;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());

        categoria = categoriaRepository.save(categoria);
        categoriaDTO.setIdCategoria(categoria.getIdCategoria());
        return categoriaDTO;
    }

    @Override
    public CategoriaDTO obtenerCategoriaPorId(Integer idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + idCategoria));
        return mapearEntidadADto(categoria);
    }

    @Override
    public List<CategoriaDTO> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(this::mapearEntidadADto).collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO actualizarCategoria(Integer idCategoria, CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + idCategoria));

        categoria.setNombre(categoriaDTO.getNombre());

        categoria = categoriaRepository.save(categoria);
        return mapearEntidadADto(categoria);
    }

    @Override
    public void eliminarCategoria(Integer idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + idCategoria));
        categoriaRepository.delete(categoria);
    }

    // Métodos de mapeo

    private CategoriaDTO mapearEntidadADto(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setIdCategoria(categoria.getIdCategoria());
        dto.setNombre(categoria.getNombre());
        if (categoria.getComidas() != null) {
            dto.setComidas(categoria.getComidas().stream()
                    .map(Comida -> Comida.getIdComida())
                    .collect(Collectors.toSet()));
        }
        return dto;
    }
}