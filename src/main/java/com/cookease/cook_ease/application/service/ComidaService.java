package com.cookease.cook_ease.application.service;
import com.cookease.cook_ease.application.dto.ComidaDTO;
import com.cookease.cook_ease.application.dto.IngredienteDTO;


import java.util.List;

public interface ComidaService {
    ComidaDTO crearComida(ComidaDTO comidaDTO);
    ComidaDTO obtenerComidaPorId(Integer idComida);
    List<ComidaDTO> listarComidas();
    ComidaDTO actualizarComida(Integer idComida, ComidaDTO comidaDTO);
    void eliminarComida(Integer idComida);

    // Operaciones sobre Ingredientes
    IngredienteDTO crearIngrediente(IngredienteDTO ingredienteDTO);
    List<IngredienteDTO> listarIngredientes();
    IngredienteDTO obtenerIngredientePorId(Integer idIngrediente);
    IngredienteDTO actualizarIngrediente(Integer idIngrediente, IngredienteDTO ingredienteDTO);
    void eliminarIngrediente(Integer idIngrediente);
}