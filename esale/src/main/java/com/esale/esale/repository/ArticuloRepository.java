package com.esale.esale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    Optional<Articulo> findByCodigoArticulo(String codigoArticulo);
}
