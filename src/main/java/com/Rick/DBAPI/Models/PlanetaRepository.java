package com.Rick.DBAPI.Models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PlanetaRepository extends JpaRepository<Planeta, Long> {
    Optional<Planeta> findByNombre(String nombre);

    @Query("SELECT p FROM Planeta p WHERE LOWER(p.nombre) = LOWER(:nombre)")
    Optional<Planeta> findByNombreIgnoreCase(@Param("nombre") String nombre);
}