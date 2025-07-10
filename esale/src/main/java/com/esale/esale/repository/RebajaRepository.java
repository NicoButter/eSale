package com.esale.esale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.Rebaja;
import com.esale.esale.model.Seccion;

public interface RebajaRepository extends JpaRepository<Rebaja, Long> {
    List<Rebaja> findByArticulo(Articulo articulo);
    List<Rebaja> findBySeccion(Seccion seccion);
    List<Rebaja> findByAplicaATodaLaTiendaTrue();
}
