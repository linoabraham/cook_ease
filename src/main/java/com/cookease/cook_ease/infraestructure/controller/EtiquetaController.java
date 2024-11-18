package com.cookease.cook_ease.infraestructure.controller;
import com.cookease.cook_ease.application.dto.EtiquetaDTO;
import com.cookease.cook_ease.application.service.EtiquetaService;
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
    public ResponseEntity<EtiquetaDTO> crearEtiqueta(@RequestBody EtiquetaDTO etiquetaDTO) {
        EtiquetaDTO creada = etiquetaService.crearEtiqueta(etiquetaDTO);
        return ResponseEntity.ok(creada);
    }

    // Obtener Etiqueta por ID
    @GetMapping("/{idEtiqueta}")
    public ResponseEntity<EtiquetaDTO> obtenerEtiquetaPorId(@PathVariable Integer idEtiqueta) {
        EtiquetaDTO etiqueta = etiquetaService.obtenerEtiquetaPorId(idEtiqueta);
        return ResponseEntity.ok(etiqueta);
    }

    // Listar todas las Etiquetas
    @GetMapping
    public ResponseEntity<List<EtiquetaDTO>> listarEtiquetas() {
        List<EtiquetaDTO> etiquetas = etiquetaService.listarEtiquetas();
        return ResponseEntity.ok(etiquetas);
    }

    // Actualizar Etiqueta
    @PutMapping("/{idEtiqueta}")
    public ResponseEntity<EtiquetaDTO> actualizarEtiqueta(
            @PathVariable Integer idEtiqueta,
            @RequestBody EtiquetaDTO etiquetaDTO) {
        EtiquetaDTO actualizado = etiquetaService.actualizarEtiqueta(idEtiqueta, etiquetaDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar Etiqueta
    @DeleteMapping("/{idEtiqueta}")
    public ResponseEntity<Void> eliminarEtiqueta(@PathVariable Integer idEtiqueta) {
        etiquetaService.eliminarEtiqueta(idEtiqueta);
        return ResponseEntity.noContent().build();
    }
}