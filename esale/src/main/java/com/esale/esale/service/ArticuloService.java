package com.esale.esale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Articulo;
import com.esale.esale.repository.ArticuloRepository;

@Service
public class ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    public List<Articulo> obtenerTodos() {
        return articuloRepository.findAll();
    }

    public Optional<Articulo> buscarPorId(Long id) {
        return articuloRepository.findById(id);
    }

    public Optional<Articulo> buscarPorCodigo(String codigo) {
        return articuloRepository.findByCodigoArticulo(codigo);
    }

    public Articulo guardar(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    public void eliminarPorId(Long id) {
        articuloRepository.deleteById(id);
    }

    // Eliminar método redundante
    // public void eliminar(Long id) { ... } // Opcional, puedes quitarlo

    // Nuevos métodos para la landing
    public List<Articulo> findFeaturedProducts() {
        return articuloRepository.findFeaturedProducts();
    }

    public List<Articulo> findProductsWithOffers() {
        return articuloRepository.findProductsWithOffers();
    }
}