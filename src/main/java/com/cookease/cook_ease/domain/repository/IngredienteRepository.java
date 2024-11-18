package com.cookease.cook_ease.domain.repository;

import com.cookease.cook_ease.domain.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
    Optional<Ingrediente> findByNombreIgnoreCase(String nombre);
    List<Ingrediente> findAllByNombreInIgnoreCase(List<String> nombres);
}