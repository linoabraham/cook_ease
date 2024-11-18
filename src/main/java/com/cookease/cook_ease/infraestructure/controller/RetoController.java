package com.cookease.cook_ease.infraestructure.controller;
import com.cookease.cook_ease.application.dto.RetoDTO;
import com.cookease.cook_ease.application.service.RetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/retos")
public class RetoController {

    @Autowired
    private RetoService retoService;

    // Crear Reto
    @PostMapping
    public ResponseEntity<RetoDTO> crearReto(@RequestBody RetoDTO retoDTO) {
        RetoDTO creado = retoService.crearReto(retoDTO);
        return ResponseEntity.ok(creado);
    }

    // Obtener Reto por ID
    @GetMapping("/{idReto}")
    public ResponseEntity<RetoDTO> obtenerRetoPorId(@PathVariable Integer idReto) {
        RetoDTO reto = retoService.obtenerRetoPorId(idReto);
        return ResponseEntity.ok(reto);
    }

    // Listar todos los Retos
    @GetMapping
    public ResponseEntity<List<RetoDTO>> listarRetos() {
        List<RetoDTO> retos = retoService.listarRetos();
        return ResponseEntity.ok(retos);
    }

    // Actualizar Reto
    @PutMapping("/{idReto}")
    public ResponseEntity<RetoDTO> actualizarReto(
            @PathVariable Integer idReto,
            @RequestBody RetoDTO retoDTO) {
        RetoDTO actualizado = retoService.actualizarReto(idReto, retoDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar Reto
    @DeleteMapping("/{idReto}")
    public ResponseEntity<Void> eliminarReto(@PathVariable Integer idReto) {
        retoService.eliminarReto(idReto);
        return ResponseEntity.noContent().build();
    }
}