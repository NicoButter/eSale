package com.esale.esale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Carrito;
import com.esale.esale.model.EstadoCarrito;
import com.esale.esale.model.Usuario;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByUsuarioAndEstado(Usuario usuario, EstadoCarrito estado);
}

