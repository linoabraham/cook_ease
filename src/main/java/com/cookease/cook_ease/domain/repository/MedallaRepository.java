package com.cookease.cook_ease.domain.repository;

import com.cookease.cook_ease.domain.model.Medalla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface MedallaRepository extends JpaRepository<Medalla, Integer> {
    List<Medalla> findByEtiquetaNombre(String nombreEtiqueta);
}