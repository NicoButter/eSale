package com.esale.esale.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.Usuario;
import com.esale.esale.model.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
    List<Valoracion> findByArticulo(Articulo articulo);
    Optional<Valoracion> findByUsuarioAndArticulo(Usuario usuario, Articulo articulo);
}
