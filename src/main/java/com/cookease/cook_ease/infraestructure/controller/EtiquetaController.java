package com.cookease.cook_ease.infraestructure.controller;
import com.cookease.cook_ease.application.dto.EtiquetaDTO;
import com.cookease.cook_ease.application.dto.EtiquetaDetalleDTO;
import com.cookease.cook_ease.application.service.EtiquetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetaController {

    @Autowired
    private EtiquetaService etiquetaService;

    // Crear Etiqueta
    @PostMapping
    public ResponseEntity<EtiquetaDTO> crearEtiqueta(@Valid @RequestBody EtiquetaDTO etiquetaDTO) {
        EtiquetaDTO creada = etiquetaService.crearEtiqueta(etiquetaDTO);
        return ResponseEntity.ok(creada);
    }

    // Obtener Etiqueta por Nombre
    @GetMapping("/{nombre}")
    public ResponseEntity<EtiquetaDetalleDTO> obtenerEtiquetaPorNombre(@PathVariable String nombre) {
        EtiquetaDetalleDTO etiqueta = etiquetaService.obtenerEtiquetaDetallePorNombre(nombre);
        return ResponseEntity.ok(etiqueta);
    }

    // Listar todas las Etiquetas
    @GetMapping
    public ResponseEntity<List<EtiquetaDTO>> listarEtiquetas() {
        List<EtiquetaDTO> etiquetas = etiquetaService.listarEtiquetas();
        return ResponseEntity.ok(etiquetas);
    }

    // Actualizar Etiqueta
    @PutMapping("/{nombre}")
    public ResponseEntity<EtiquetaDTO> actualizarEtiqueta(
            @PathVariable String nombre,
            @Valid @RequestBody EtiquetaDTO etiquetaDTO) {
        EtiquetaDTO actualizado = etiquetaService.actualizarEtiqueta(nombre, etiquetaDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar Etiqueta
    @DeleteMapping("/{nombre}")
    public ResponseEntity<Void> eliminarEtiqueta(@PathVariable String nombre) {
        etiquetaService.eliminarEtiqueta(nombre);
        return ResponseEntity.noContent().build();
    }
}