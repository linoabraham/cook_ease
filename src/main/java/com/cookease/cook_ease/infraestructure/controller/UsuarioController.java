package com.cookease.cook_ease.infraestructure.controller;

import com.cookease.cook_ease.application.dto.RetoDTO;
import com.cookease.cook_ease.application.dto.UsuarioDTO;
import com.cookease.cook_ease.application.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Crear Usuario
    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO creado = usuarioService.crearUsuario(usuarioDTO);
        return ResponseEntity.ok(creado);
    }

    // Obtener Usuario por ID
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Integer idUsuario) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
        return ResponseEntity.ok(usuario);
    }

    // Listar todos los Usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // Actualizar Usuario
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(
            @PathVariable Integer idUsuario,
            @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO actualizado = usuarioService.actualizarUsuario(idUsuario, usuarioDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar Usuario
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer idUsuario) {
        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }

    // Asignar Etiqueta a Usuario
    @PostMapping("/{idUsuario}/etiquetas/{idEtiqueta}")
    public ResponseEntity<UsuarioDTO> asignarEtiqueta(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idEtiqueta) {
        UsuarioDTO usuario = usuarioService.asignarEtiqueta(idUsuario, idEtiqueta);
        return ResponseEntity.ok(usuario);
    }

    // Remover Etiqueta de Usuario
    @DeleteMapping("/{idUsuario}/etiquetas/{idEtiqueta}")
    public ResponseEntity<UsuarioDTO> removerEtiqueta(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idEtiqueta) {
        UsuarioDTO usuario = usuarioService.removerEtiqueta(idUsuario, idEtiqueta);
        return ResponseEntity.ok(usuario);
    }

    // Obtener Retos de Usuario por Etiqueta y Nivel de Medalla
    @GetMapping("/{idUsuario}/retos")
    public ResponseEntity<List<RetoDTO>> obtenerRetosPorUsuario(
            @PathVariable Integer idUsuario,
            @RequestParam Integer idEtiqueta,
            @RequestParam String nivelMedalla) {
        List<RetoDTO> retos = usuarioService.obtenerRetosPorUsuario(idUsuario, idEtiqueta, nivelMedalla);
        return ResponseEntity.ok(retos);
    }
}