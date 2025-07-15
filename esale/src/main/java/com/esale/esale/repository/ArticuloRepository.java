package com.esale.esale.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.esale.esale.model.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    Optional<Articulo> findByCodigoArticulo(String codigoArticulo);

    @Query("SELECT a FROM Articulo a JOIN a.destacados d WHERE d.fechaInicio <= CURRENT_DATE AND d.fechaFin >= CURRENT_DATE")
    List<Articulo> findFeaturedProducts();

    @Query("SELECT a FROM Articulo a JOIN a.rebaja r WHERE r.porcentajeDescuento IS NOT NULL")
    List<Articulo> findProductsWithOffers();
}
