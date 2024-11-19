package com.cookease.cook_ease.application.service.impl;

import com.cookease.cook_ease.application.dto.ComidaDTO;
import com.cookease.cook_ease.application.dto.IngredienteDTO;
import com.cookease.cook_ease.application.service.ComidaService;
import com.cookease.cook_ease.domain.model.Categoria;
import com.cookease.cook_ease.domain.model.Comida;
import com.cookease.cook_ease.domain.model.Ingrediente;
import com.cookease.cook_ease.domain.repository.CategoriaRepository;
import com.cookease.cook_ease.domain.repository.ComidaRepository;
import com.cookease.cook_ease.domain.repository.IngredienteRepository;
import com.cookease.cook_ease.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComidaServiceImpl implements ComidaService {

    @Autowired
    private ComidaRepository comidaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Override
    public ComidaDTO crearComida(ComidaDTO comidaDTO) {
        Comida comida = new Comida();
        comida.setNumber(comidaDTO.getNombre());
        comida.setReceta(comidaDTO.getReceta());
        comida.setInstrucciones(comidaDTO.getInstrucciones());
        comida.setDescripcionNutritional(comidaDTO.getDescripcionNutritional());

        // Asignar categoría por nombre
        Categoria categoria = categoriaRepository.findAll().stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(comidaDTO.getCategoria()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con nombre: " + comidaDTO.getCategoria()));
        comida.setCategoria(categoria);

        // Asignar ingredientes por nombres
        if (comidaDTO.getIngredientes() != null && !comidaDTO.getIngredientes().isEmpty()) {
            List<String> nombresIngredientes = comidaDTO.getIngredientes().stream()
                    .map(String::trim)
                    .collect(Collectors.toList());

            List<Ingrediente> ingredientes = ingredienteRepository.findAllByNombreInIgnoreCase(nombresIngredientes);

            // Verificar si todos los ingredientes fueron encontrados
            Set<String> encontrados = ingredientes.stream()
                    .map(Ingrediente::getNombre)
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            List<String> faltantes = nombresIngredientes.stream()
                    .filter(nombre -> !encontrados.contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());

            if (!faltantes.isEmpty()) {
                throw new ResourceNotFoundException("Los siguientes ingredientes no fueron encontrados: " + faltantes);
            }

            comida.setIngredientes(new HashSet<>(ingredientes));
        }

        // Manejar la imagenUrl
        if (comidaDTO.getImagenUrl() != null && !comidaDTO.getImagenUrl().isEmpty()) {
            if (!validarImagenUrl(comidaDTO.getImagenUrl())) {
                throw new IllegalArgumentException("La imagenUrl proporcionada no apunta a una imagen válida.");
            }
            comida.setImagenUrl(comidaDTO.getImagenUrl());
        } else {
            comida.setImagenUrl(null); // Opcional: establecer una imagen por defecto
        }

        comida = comidaRepository.save(comida);
        return mapearEntidadADto(comida);
    }

    @Override
    public ComidaDTO obtenerComidaPorId(Integer idComida) {
        Comida comida = comidaRepository.findById(idComida)
                .orElseThrow(() -> new ResourceNotFoundException("Comida no encontrada con id: " + idComida));
        return mapearEntidadADto(comida);
    }

    @Override
    public List<ComidaDTO> listarComidas() {
        List<Comida> comidas = comidaRepository.findAll();
        return comidas.stream().map(this::mapearEntidadADto).collect(Collectors.toList());
    }

    @Override
    public ComidaDTO actualizarComida(Integer idComida, ComidaDTO comidaDTO) {
        Comida comida = comidaRepository.findById(idComida)
                .orElseThrow(() -> new ResourceNotFoundException("Comida no encontrada con id: " + idComida));

        // Actualizar campos básicos
        comida.setNumber(comidaDTO.getNombre());
        comida.setReceta(comidaDTO.getReceta());
        comida.setInstrucciones(comidaDTO.getInstrucciones());
        comida.setDescripcionNutritional(comidaDTO.getDescripcionNutritional());

        // Actualizar categoría si se proporciona
        if (comidaDTO.getCategoria() != null && !comidaDTO.getCategoria().isEmpty()) {
            Categoria categoria = categoriaRepository.findAll().stream()
                    .filter(c -> c.getNombre().equalsIgnoreCase(comidaDTO.getCategoria()))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con nombre: " + comidaDTO.getCategoria()));
            comida.setCategoria(categoria);
        }

        // Actualizar ingredientes si se proporcionan
        if (comidaDTO.getIngredientes() != null) {
            List<String> nombresIngredientes = comidaDTO.getIngredientes().stream()
                    .map(String::trim)
                    .collect(Collectors.toList());

            List<Ingrediente> ingredientes = ingredienteRepository.findAllByNombreInIgnoreCase(nombresIngredientes);

            // Verificar si todos los ingredientes fueron encontrados
            Set<String> encontrados = ingredientes.stream()
                    .map(Ingrediente::getNombre)
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            List<String> faltantes = nombresIngredientes.stream()
                    .filter(nombre -> !encontrados.contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());

            if (!faltantes.isEmpty()) {
                throw new ResourceNotFoundException("Los siguientes ingredientes no fueron encontrados: " + faltantes);
            }

            comida.setIngredientes(new HashSet<>(ingredientes));
        }

        // Manejar la imagenUrl
        if (comidaDTO.getImagenUrl() != null && !comidaDTO.getImagenUrl().isEmpty()) {
            if (!validarImagenUrl(comidaDTO.getImagenUrl())) {
                throw new IllegalArgumentException("La imagenUrl proporcionada no apunta a una imagen válida.");
            }
            comida.setImagenUrl(comidaDTO.getImagenUrl());
        } else {
            // Opcional: mantener la imagen existente o establecer una imagen por defecto
            // Por ejemplo, no hacer nada si no se proporciona una nueva imagenUrl
        }

        comida = comidaRepository.save(comida);
        return mapearEntidadADto(comida);
    }

    @Override
    public void eliminarComida(Integer idComida) {
        Comida comida = comidaRepository.findById(idComida)
                .orElseThrow(() -> new ResourceNotFoundException("Comida no encontrada con id: " + idComida));
        comidaRepository.delete(comida);
    }

    // Métodos para gestionar Ingredientes

    @Override
    public IngredienteDTO crearIngrediente(IngredienteDTO ingredienteDTO) {
        // Verificar si el ingrediente ya existe
        Optional<Ingrediente> existente = ingredienteRepository.findByNombreIgnoreCase(ingredienteDTO.getNombre());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("El ingrediente ya existe: " + ingredienteDTO.getNombre());
        }

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre(ingredienteDTO.getNombre());

        ingrediente = ingredienteRepository.save(ingrediente);
        ingredienteDTO.setIdIngrediente(ingrediente.getIdIngrediente());
        return ingredienteDTO;
    }

    @Override
    public List<IngredienteDTO> listarIngredientes() {
        List<Ingrediente> ingredientes = ingredienteRepository.findAll();
        return ingredientes.stream().map(this::mapearIngredienteADto).collect(Collectors.toList());
    }

    @Override
    public IngredienteDTO obtenerIngredientePorId(Integer idIngrediente) {
        Ingrediente ingrediente = ingredienteRepository.findById(idIngrediente)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado con id: " + idIngrediente));
        return mapearIngredienteADto(ingrediente);
    }

    @Override
    public IngredienteDTO actualizarIngrediente(Integer idIngrediente, IngredienteDTO ingredienteDTO) {
        Ingrediente ingrediente = ingredienteRepository.findById(idIngrediente)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado con id: " + idIngrediente));

        ingrediente.setNombre(ingredienteDTO.getNombre());

        ingrediente = ingredienteRepository.save(ingrediente);
        return mapearIngredienteADto(ingrediente);
    }

    @Override
    public void eliminarIngrediente(Integer idIngrediente) {
        Ingrediente ingrediente = ingredienteRepository.findById(idIngrediente)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado con id: " + idIngrediente));
        ingredienteRepository.delete(ingrediente);
    }

    // Métodos de mapeo

    private ComidaDTO mapearEntidadADto(Comida comida) {
        ComidaDTO dto = new ComidaDTO();
        dto.setIdComida(comida.getIdComida());
        dto.setNombre(comida.getNumber());
        dto.setReceta(comida.getReceta());
        dto.setInstrucciones(comida.getInstrucciones());
        dto.setDescripcionNutritional(comida.getDescripcionNutritional());
        dto.setCategoria(comida.getCategoria().getNombre());

        if (comida.getIngredientes() != null) {
            List<String> nombresIngredientes = comida.getIngredientes().stream()
                    .map(Ingrediente::getNombre)
                    .collect(Collectors.toList());
            dto.setIngredientes(nombresIngredientes);
        }

        dto.setImagenUrl(comida.getImagenUrl());
        return dto;
    }

    private IngredienteDTO mapearIngredienteADto(Ingrediente ingrediente) {
        IngredienteDTO dto = new IngredienteDTO();
        dto.setIdIngrediente(ingrediente.getIdIngrediente());
        dto.setNombre(ingrediente.getNombre());
        return dto;
    }

    // Método para validar que la imagenUrl apunta a una imagen válida
    private boolean validarImagenUrl(String url) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.HEAD, null, Void.class);
            String contentType = response.getHeaders().getContentType().toString();
            return contentType.startsWith("image/");
        } catch (Exception e) {
            return false;
        }
    }
}