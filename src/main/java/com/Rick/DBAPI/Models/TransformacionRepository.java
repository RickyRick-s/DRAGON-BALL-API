package com.Rick.DBAPI.Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TransformacionRepository extends JpaRepository<Transformacion, Long> {
    Optional<Transformacion> findByNombre(String nombre);
}
