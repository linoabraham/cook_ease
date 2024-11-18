package com.cookease.cook_ease.infraestructure.controller;

import com.cookease.cook_ease.application.dto.CategoriaDTO;
import com.cookease.cook_ease.application.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Crear Categoría
    @PostMapping
    public ResponseEntity<CategoriaDTO> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO creada = categoriaService.crearCategoria(categoriaDTO);
        return ResponseEntity.ok(creada);
    }

    // Obtener Categoría por ID
    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDTO> obtenerCategoriaPorId(@PathVariable Integer idCategoria) {
        CategoriaDTO categoria = categoriaService.obtenerCategoriaPorId(idCategoria);
        return ResponseEntity.ok(categoria);
    }

    // Listar todas las Categorías
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        List<CategoriaDTO> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    // Actualizar Categoría
    @PutMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDTO> actualizarCategoria(
            @PathVariable Integer idCategoria,
            @Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO actualizado = categoriaService.actualizarCategoria(idCategoria, categoriaDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar Categoría
    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer idCategoria) {
        categoriaService.eliminarCategoria(idCategoria);
        return ResponseEntity.noContent().build();
    }
}