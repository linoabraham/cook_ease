package com.cookease.cook_ease.domain.repository;
import com.cookease.cook_ease.domain.model.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Override
    @EntityGraph(attributePaths = {"etiquetas"})
    List<Usuario> findAll();
}