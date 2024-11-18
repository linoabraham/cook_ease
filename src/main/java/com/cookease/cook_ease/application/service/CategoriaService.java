package com.cookease.cook_ease.application.service;

import com.cookease.cook_ease.application.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {
    CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO);
    CategoriaDTO obtenerCategoriaPorId(Integer idCategoria);
    List<CategoriaDTO> listarCategorias();
    CategoriaDTO actualizarCategoria(Integer idCategoria, CategoriaDTO categoriaDTO);
    void eliminarCategoria(Integer idCategoria);
}