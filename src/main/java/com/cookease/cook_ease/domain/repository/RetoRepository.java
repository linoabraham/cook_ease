package com.cookease.cook_ease.domain.repository;

import com.cookease.cook_ease.domain.model.Reto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetoRepository extends JpaRepository<Reto, Integer> {
    List<Reto> findByMedallaIdMedalla(Integer idMedalla);
}