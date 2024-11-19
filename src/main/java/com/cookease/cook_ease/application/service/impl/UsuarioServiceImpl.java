package com.cookease.cook_ease.application.service.impl;
import com.cookease.cook_ease.application.service.UsuarioService;
import com.cookease.cook_ease.application.dto.MedallaDTO;
import com.cookease.cook_ease.application.dto.UsuarioDTO;
import com.cookease.cook_ease.application.dto.RetoDTO;
import com.cookease.cook_ease.application.dto.EtiquetaDTO;
import com.cookease.cook_ease.domain.model.Etiqueta;
import com.cookease.cook_ease.domain.model.Medalla;
import com.cookease.cook_ease.domain.model.Reto;
import com.cookease.cook_ease.domain.model.Usuario;
import com.cookease.cook_ease.domain.repository.EtiquetaRepository;
import com.cookease.cook_ease.domain.repository.RetoRepository;
import com.cookease.cook_ease.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @Autowired
    private RetoRepository retoRepository;

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        mapearDtoAEntidad(usuarioDTO, usuario);

        // Asignar etiquetas si se proporcionan
        if (usuarioDTO.getEtiquetas() != null && !usuarioDTO.getEtiquetas().isEmpty()) {
            Set<Etiqueta> etiquetas = new HashSet<>(etiquetaRepository.findAllById(usuarioDTO.getEtiquetas()));
            usuario.setEtiquetas(etiquetas);
        }

        usuario = usuarioRepository.save(usuario);
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + idUsuario));
        return mapearEntidadADto(usuario);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(this::mapearEntidadADto).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO actualizarUsuario(Integer idUsuario, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + idUsuario));

        mapearDtoAEntidad(usuarioDTO, usuario);

        // Actualizar etiquetas si se proporcionan
        if (usuarioDTO.getEtiquetas() != null) {
            Set<Etiqueta> etiquetas = new HashSet<>(etiquetaRepository.findAllById(usuarioDTO.getEtiquetas()));
            usuario.setEtiquetas(etiquetas);
        }

        usuario = usuarioRepository.save(usuario);
        return mapearEntidadADto(usuario);
    }

    @Override
    public void eliminarUsuario(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + idUsuario));
        usuarioRepository.delete(usuario);
    }

    @Override
    public UsuarioDTO asignarEtiqueta(Integer idUsuario, String nombreEtiqueta) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + idUsuario));
        Etiqueta etiqueta = etiquetaRepository.findById(nombreEtiqueta)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con nombre: " + nombreEtiqueta));

        usuario.getEtiquetas().add(etiqueta);
        usuario = usuarioRepository.save(usuario);
        return mapearEntidadADto(usuario);
    }

    @Override
    public UsuarioDTO removerEtiqueta(Integer idUsuario, String nombreEtiqueta) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + idUsuario));
        Etiqueta etiqueta = etiquetaRepository.findById(nombreEtiqueta)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con nombre: " + nombreEtiqueta));

        usuario.getEtiquetas().remove(etiqueta);
        usuario = usuarioRepository.save(usuario);
        return mapearEntidadADto(usuario);
    }

    @Override
    public List<RetoDTO> obtenerRetosPorUsuario(Integer idUsuario, String nombreEtiqueta, String nivelMedalla) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + idUsuario));

        // Verificar que el usuario tiene la etiqueta
        Etiqueta etiqueta = usuario.getEtiquetas().stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombreEtiqueta))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no asignada al usuario"));

        // Filtrar medallas por nivel
        List<Medalla> medallas = etiqueta.getMedallas().stream()
                .filter(m -> m.getNivel().equalsIgnoreCase(nivelMedalla))
                .collect(Collectors.toList());

        // Obtener retos asociados a las medallas
        List<RetoDTO> retosDTO = new ArrayList<>();
        for (Medalla medalla : medallas) {
            List<Reto> retos = retoRepository.findByMedallaIdMedalla(medalla.getIdMedalla());
            retosDTO.addAll(retos.stream().map(this::mapearRetoAtoDTO).collect(Collectors.toList()));
        }

        return retosDTO;
    }

    // Métodos de mapeo

    private void mapearDtoAEntidad(UsuarioDTO dto, Usuario entidad) {
        entidad.setNombre(dto.getNombre());
        entidad.setApellido(dto.getApellido());
        entidad.setGender(dto.getGender());
        entidad.setAge(dto.getAge());
        entidad.setHeight(dto.getHeight());
        entidad.setWeight(dto.getWeight());
        entidad.setFamily_history_with_overweight(dto.getFamily_history_with_overweight());
        entidad.setFAVC(dto.getFAVC());
        entidad.setFCVC(dto.getFCVC());
        entidad.setNCP(dto.getNCP());
        entidad.setCAEC(dto.getCAEC());
        entidad.setSMOKE(dto.getSMOKE());
        entidad.setCH2O(dto.getCH2O());
        entidad.setSCC(dto.getSCC());
        entidad.setFAF(dto.getFAF());
        entidad.setTUE(dto.getTUE());
        entidad.setCALC(dto.getCALC());
        entidad.setMTRANS(dto.getMTRANS());
    }

    private UsuarioDTO mapearEntidadADto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setGender(usuario.getGender());
        dto.setAge(usuario.getAge());
        dto.setHeight(usuario.getHeight());
        dto.setWeight(usuario.getWeight());
        dto.setFamily_history_with_overweight(usuario.getFamily_history_with_overweight());
        dto.setFAVC(usuario.getFAVC());
        dto.setFCVC(usuario.getFCVC());
        dto.setNCP(usuario.getNCP());
        dto.setCAEC(usuario.getCAEC());
        dto.setSMOKE(usuario.getSMOKE());
        dto.setCH2O(usuario.getCH2O());
        dto.setSCC(usuario.getSCC());
        dto.setFAF(usuario.getFAF());
        dto.setTUE(usuario.getTUE());
        dto.setCALC(usuario.getCALC());
        dto.setMTRANS(usuario.getMTRANS());
        if (usuario.getEtiquetas() != null) {
            dto.setEtiquetas(usuario.getEtiquetas().stream()
                    .map(Etiqueta::getNombre)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    private RetoDTO mapearRetoAtoDTO(Reto reto) {
        RetoDTO dto = new RetoDTO();
        dto.setIdReto(reto.getIdReto());
        dto.setDescripcion(reto.getDescripcion());
        dto.setImgUrl(reto.getImgUrl());
        dto.setIdMedalla(reto.getMedalla().getIdMedalla());
        return dto;
    }
}