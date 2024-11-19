package com.cookease.cook_ease.application.service;
import com.cookease.cook_ease.application.dto.RetoDTO;
import com.cookease.cook_ease.application.dto.UsuarioDTO;


import java.util.List;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO obtenerUsuarioPorId(Integer idUsuario);
    List<UsuarioDTO> listarUsuarios();
    UsuarioDTO actualizarUsuario(Integer idUsuario, UsuarioDTO usuarioDTO);
    void eliminarUsuario(Integer idUsuario);
    UsuarioDTO asignarEtiqueta(Integer idUsuario, String nombreEtiqueta);
    UsuarioDTO removerEtiqueta(Integer idUsuario, String nombreEtiqueta);
    List<RetoDTO> obtenerRetosPorUsuario(Integer idUsuario, String nombreEtiqueta, String nivelMedalla);
}