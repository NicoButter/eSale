package com.esale.esale.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.ArticuloDestacado;

public interface ArticuloDestacadoRepository extends JpaRepository<ArticuloDestacado, Long> {
    List<ArticuloDestacado> findByArticulo(Articulo articulo);
    List<ArticuloDestacado> findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(LocalDate start, LocalDate end);
}
