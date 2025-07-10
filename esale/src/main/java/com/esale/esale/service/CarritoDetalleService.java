package com.esale.esale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.Carrito;
import com.esale.esale.model.CarritoDetalle;
import com.esale.esale.repository.CarritoDetalleRepository;

@Service
public class CarritoDetalleService {

    @Autowired
    private CarritoDetalleRepository carritoDetalleRepository;

    public List<CarritoDetalle> obtenerItemsPorCarrito(Carrito carrito) {
        return carritoDetalleRepository.findByCarrito(carrito);
    }

    public Optional<CarritoDetalle> buscarPorCarritoYArticulo(Carrito carrito, Articulo articulo) {
        return carritoDetalleRepository.findByCarritoAndArticulo(carrito, articulo);
    }

    public CarritoDetalle guardar(CarritoDetalle detalle) {
        return carritoDetalleRepository.save(detalle);
    }

    public void eliminar(Long id) {
        carritoDetalleRepository.deleteById(id);
    }
}

