package com.cookease.cook_ease.domain.repository;

import com.cookease.cook_ease.domain.model.Categoria;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    // Método para cargar las comidas de forma eager usando EntityGraph
    @Override
    @EntityGraph(attributePaths = {"comidas"})
    List<Categoria> findAll();
}