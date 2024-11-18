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
@Transactional // Asegura que todos los métodos sean transaccionales
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
        UsuarioDTO dto = mapearEntidadADto(usuario);
        return dto;
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
    public UsuarioDTO asignarEtiqueta(Integer idUsuario, Integer idEtiqueta) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + idUsuario));
        Etiqueta etiqueta = etiquetaRepository.findById(idEtiqueta)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con id: " + idEtiqueta));

        usuario.getEtiquetas().add(etiqueta);
        usuario = usuarioRepository.save(usuario);
        return mapearEntidadADto(usuario);
    }

    @Override
    public UsuarioDTO removerEtiqueta(Integer idUsuario, Integer idEtiqueta) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + idUsuario));
        Etiqueta etiqueta = etiquetaRepository.findById(idEtiqueta)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con id: " + idEtiqueta));

        usuario.getEtiquetas().remove(etiqueta);
        usuario = usuarioRepository.save(usuario);
        return mapearEntidadADto(usuario);
    }

    @Override
    public List<RetoDTO> obtenerRetosPorUsuario(Integer idUsuario, Integer idEtiqueta, String nivelMedalla) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + idUsuario));

        // Verificar que el usuario tiene la etiqueta
        Etiqueta etiqueta = usuario.getEtiquetas().stream()
                .filter(e -> e.getIdEtiqueta().equals(idEtiqueta))
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
        entidad.setFirstName(dto.getFirstName());
        entidad.setLastName(dto.getLastName());
        entidad.setGender(dto.getGender());
        entidad.setBirthDate(dto.getBirthDate());
        entidad.setEmail(dto.getEmail());
        entidad.setHeight(dto.getHeight());
        entidad.setWeight(dto.getWeight());
        entidad.setActivityLevel(dto.getActivityLevel());
        entidad.setFitnessGoals(dto.getFitnessGoals());
        entidad.setAllergies(dto.getAllergies());
        entidad.setSleepHours(dto.getSleepHours());
        entidad.setFixedMealSchedule(dto.getFixedMealSchedule());
        entidad.setMealPreparationTime(dto.getMealPreparationTime());
        entidad.setOutOfHomeMealsFrequency(dto.getOutOfHomeMealsFrequency());
        entidad.setAccessToHealthyStores(dto.getAccessToHealthyStores());
        entidad.setPreferredProtein(dto.getPreferredProtein());
        entidad.setFruitConsumptionFrequency(dto.getFruitConsumptionFrequency());
    }

    private UsuarioDTO mapearEntidadADto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setFirstName(usuario.getFirstName());
        dto.setLastName(usuario.getLastName());
        dto.setGender(usuario.getGender());
        dto.setBirthDate(usuario.getBirthDate());
        dto.setEmail(usuario.getEmail());
        dto.setHeight(usuario.getHeight());
        dto.setWeight(usuario.getWeight());
        dto.setActivityLevel(usuario.getActivityLevel());
        dto.setFitnessGoals(usuario.getFitnessGoals());
        dto.setAllergies(usuario.getAllergies());
        dto.setSleepHours(usuario.getSleepHours());
        dto.setFixedMealSchedule(usuario.getFixedMealSchedule());
        dto.setMealPreparationTime(usuario.getMealPreparationTime());
        dto.setOutOfHomeMealsFrequency(usuario.getOutOfHomeMealsFrequency());
        dto.setAccessToHealthyStores(usuario.getAccessToHealthyStores());
        dto.setPreferredProtein(usuario.getPreferredProtein());
        dto.setFruitConsumptionFrequency(usuario.getFruitConsumptionFrequency());
        if (usuario.getEtiquetas() != null) {
            dto.setEtiquetas(usuario.getEtiquetas().stream()
                    .map(Etiqueta::getIdEtiqueta)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    private RetoDTO mapearRetoAtoDTO(Reto reto) {
        RetoDTO dto = new RetoDTO();
        dto.setIdReto(reto.getIdReto());
        dto.setDescripcion(reto.getDescripcion());
        dto.setIdMedalla(reto.getMedalla().getIdMedalla());
        return dto;
    }
}