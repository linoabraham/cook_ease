package com.cookease.cook_ease.infraestructure.controller;
import com.cookease.cook_ease.application.dto.MedallaDTO;
import com.cookease.cook_ease.application.service.MedallaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/medallas")
public class MedallaController {

    @Autowired
    private MedallaService medallaService;

    // Crear Medalla
    @PostMapping
    public ResponseEntity<MedallaDTO> crearMedalla(@Valid @RequestBody MedallaDTO medallaDTO) {
        MedallaDTO creada = medallaService.crearMedalla(medallaDTO);
        return ResponseEntity.ok(creada);
    }

    // Obtener Medalla por ID
    @GetMapping("/{idMedalla}")
    public ResponseEntity<MedallaDTO> obtenerMedallaPorId(@PathVariable Integer idMedalla) {
        MedallaDTO medalla = medallaService.obtenerMedallaPorId(idMedalla);
        return ResponseEntity.ok(medalla);
    }

    // Listar todas las Medallas
    @GetMapping
    public ResponseEntity<List<MedallaDTO>> listarMedallas() {
        List<MedallaDTO> medallas = medallaService.listarMedallas();
        return ResponseEntity.ok(medallas);
    }

    // Actualizar Medalla
    @PutMapping("/{idMedalla}")
    public ResponseEntity<MedallaDTO> actualizarMedalla(
            @PathVariable Integer idMedalla,
            @Valid @RequestBody MedallaDTO medallaDTO) {
        MedallaDTO actualizado = medallaService.actualizarMedalla(idMedalla, medallaDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar Medalla
    @DeleteMapping("/{idMedalla}")
    public ResponseEntity<Void> eliminarMedalla(@PathVariable Integer idMedalla) {
        medallaService.eliminarMedalla(idMedalla);
        return ResponseEntity.noContent().build();
    }
}