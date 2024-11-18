package com.cookease.cook_ease.infraestructure.controller;

import com.cookease.cook_ease.application.dto.ComidaDTO;
import com.cookease.cook_ease.application.dto.IngredienteDTO;
import com.cookease.cook_ease.application.service.ComidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/comidas")
public class ComidaController {

    @Autowired
    private ComidaService comidaService;

    // ----- Endpoints de Comidas -----

    /**
     * Crear una nueva Comida con una URL de imagen externa.
     *
     * @param comidaDTO Datos de la comida.
     * @return ComidaDTO creada.
     */
    @PostMapping
    public ResponseEntity<ComidaDTO> crearComida(@Valid @RequestBody ComidaDTO comidaDTO) {
        ComidaDTO creada = comidaService.crearComida(comidaDTO);
        return ResponseEntity.ok(creada);
    }

    /**
     * Obtener una Comida por su ID.
     *
     * @param idComida ID de la comida.
     * @return ComidaDTO correspondiente.
     */
    @GetMapping("/{idComida}")
    public ResponseEntity<ComidaDTO> obtenerComidaPorId(@PathVariable Integer idComida) {
        ComidaDTO comida = comidaService.obtenerComidaPorId(idComida);
        return ResponseEntity.ok(comida);
    }

    /**
     * Listar todas las Comidas.
     *
     * @return Lista de ComidaDTO.
     */
    @GetMapping
    public ResponseEntity<List<ComidaDTO>> listarComidas() {
        List<ComidaDTO> comidas = comidaService.listarComidas();
        return ResponseEntity.ok(comidas);
    }

    /**
     * Actualizar una Comida existente con una URL de imagen externa.
     *
     * @param idComida  ID de la comida a actualizar.
     * @param comidaDTO Nuevos datos de la comida.
     * @return ComidaDTO actualizada.
     */
    @PutMapping("/{idComida}")
    public ResponseEntity<ComidaDTO> actualizarComida(
            @PathVariable Integer idComida,
            @Valid @RequestBody ComidaDTO comidaDTO) {
        ComidaDTO actualizado = comidaService.actualizarComida(idComida, comidaDTO);
        return ResponseEntity.ok(actualizado);
    }

    /**
     * Eliminar una Comida por su ID.
     *
     * @param idComida ID de la comida a eliminar.
     * @return Estado sin contenido.
     */
    @DeleteMapping("/{idComida}")
    public ResponseEntity<Void> eliminarComida(@PathVariable Integer idComida) {
        comidaService.eliminarComida(idComida);
        return ResponseEntity.noContent().build();
    }

    // ----- Endpoints de Ingredientes -----

    /**
     * Crear un nuevo Ingrediente.
     *
     * @param ingredienteDTO Datos del ingrediente.
     * @return IngredienteDTO creado.
     */
    @PostMapping("/ingredientes")
    public ResponseEntity<IngredienteDTO> crearIngrediente(@Valid @RequestBody IngredienteDTO ingredienteDTO) {
        IngredienteDTO creado = comidaService.crearIngrediente(ingredienteDTO);
        return ResponseEntity.ok(creado);
    }

    /**
     * Obtener un Ingrediente por su ID.
     *
     * @param idIngrediente ID del ingrediente.
     * @return IngredienteDTO correspondiente.
     */
    @GetMapping("/ingredientes/{idIngrediente}")
    public ResponseEntity<IngredienteDTO> obtenerIngredientePorId(@PathVariable Integer idIngrediente) {
        IngredienteDTO ingrediente = comidaService.obtenerIngredientePorId(idIngrediente);
        return ResponseEntity.ok(ingrediente);
    }

    /**
     * Listar todos los Ingredientes.
     *
     * @return Lista de IngredienteDTO.
     */
    @GetMapping("/ingredientes")
    public ResponseEntity<List<IngredienteDTO>> listarIngredientes() {
        List<IngredienteDTO> ingredientes = comidaService.listarIngredientes();
        return ResponseEntity.ok(ingredientes);
    }

    /**
     * Actualizar un Ingrediente existente.
     *
     * @param idIngrediente  ID del ingrediente a actualizar.
     * @param ingredienteDTO Nuevos datos del ingrediente.
     * @return IngredienteDTO actualizado.
     */
    @PutMapping("/ingredientes/{idIngrediente}")
    public ResponseEntity<IngredienteDTO> actualizarIngrediente(
            @PathVariable Integer idIngrediente,
            @Valid @RequestBody IngredienteDTO ingredienteDTO) {
        IngredienteDTO actualizado = comidaService.actualizarIngrediente(idIngrediente, ingredienteDTO);
        return ResponseEntity.ok(actualizado);
    }

    /**
     * Eliminar un Ingrediente por su ID.
     *
     * @param idIngrediente ID del ingrediente a eliminar.
     * @return Estado sin contenido.
     */
    @DeleteMapping("/ingredientes/{idIngrediente}")
    public ResponseEntity<Void> eliminarIngrediente(@PathVariable Integer idIngrediente) {
        comidaService.eliminarIngrediente(idIngrediente);
        return ResponseEntity.noContent().build();
    }
}