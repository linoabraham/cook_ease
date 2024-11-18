package com.cookease.cook_ease.domain.repository;

import com.cookease.cook_ease.domain.model.Comida;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComidaRepository extends JpaRepository<Comida, Integer> {

    // Método para cargar ingredientes y categoría de forma eager
    @Override
    @EntityGraph(attributePaths = {"ingredientes", "categoria"})
    List<Comida> findAll();
}