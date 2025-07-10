package com.esale.esale.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.Carrito;
import com.esale.esale.model.CarritoDetalle;

public interface CarritoDetalleRepository extends JpaRepository<CarritoDetalle, Long> {
    List<CarritoDetalle> findByCarrito(Carrito carrito);
    Optional<CarritoDetalle> findByCarritoAndArticulo(Carrito carrito, Articulo articulo);
}

