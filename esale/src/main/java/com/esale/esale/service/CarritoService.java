package com.esale.esale.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Carrito;
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
}
