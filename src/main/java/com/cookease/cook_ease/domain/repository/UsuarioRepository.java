package com.cookease.cook_ease.domain.repository;
import com.cookease.cook_ease.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {}