package com.esale.esale.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.Carrito;
import com.esale.esale.model.CarritoDetalle;
import com.esale.esale.model.EstadoCarrito;
import com.esale.esale.model.Usuario;
import com.esale.esale.repository.CarritoRepository;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public Optional<Carrito> buscarCarritoActivo(Usuario usuario) {
        return carritoRepository.findByUsuarioAndEstado(usuario, EstadoCarrito.ACTIVO);
    }

    public Carrito guardar(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void eliminar(Long id) {
        carritoRepository.deleteById(id);
    }

    public Optional<Carrito> obtenerPorUsuario(Usuario usuario) {
        return carritoRepository.findByUsuario(usuario);
    }

    public Carrito crearCarrito(Usuario usuario) {
        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        return carritoRepository.save(carrito);
    }

    public void agregarAlCarrito(Carrito carrito, Articulo articulo, int cantidad) {
        // Verificá si ya existe el ítem
        Optional<CarritoDetalle> existente = carrito.getDetalles().stream()
                .filter(detalle -> detalle.getArticulo().getId().equals(articulo.getId()))
                .findFirst();

        if (existente.isPresent()) {
            CarritoDetalle detalle = existente.get();
            detalle.setCantidad(detalle.getCantidad() + cantidad);
        } else {
            CarritoDetalle nuevo = new CarritoDetalle();
            nuevo.setCarrito(carrito);
            nuevo.setArticulo(articulo);
            nuevo.setCantidad(cantidad);
            carrito.getDetalles().add(nuevo);
        }

        carritoRepository.save(carrito); // Guardamos el carrito actualizado
    }

    public void eliminarDelCarrito(Carrito carrito, Articulo articulo) {
        carrito.getDetalles().removeIf(detalle -> detalle.getArticulo().getId().equals(articulo.getId()));
        carritoRepository.save(carrito);
    }

}
