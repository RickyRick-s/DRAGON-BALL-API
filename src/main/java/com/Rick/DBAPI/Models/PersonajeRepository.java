package com.Rick.DBAPI.Models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    // Buscar personaje por nombre
    Optional<Personaje> findByNombre(String nombre);

    // Obtener todos los personajes de un planeta específico
    @Query("SELECT p FROM Personaje p WHERE p.planetaOrigen IS NOT NULL AND LOWER(p.planetaOrigen.nombre) = LOWER(:nombrePlaneta)")
    List<Personaje> personajesPorPlaneta(@Param("nombrePlaneta") String nombrePlaneta);

}





