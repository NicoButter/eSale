package com.esale.esale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Seccion;

public interface SeccionRepository extends JpaRepository<Seccion, Long> {
    Optional<Seccion> findByDescripcion(String descripcion);
}
