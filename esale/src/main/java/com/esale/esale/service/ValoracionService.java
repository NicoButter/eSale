package com.esale.esale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.Usuario;
import com.esale.esale.model.Valoracion;
import com.esale.esale.repository.ValoracionRepository;

@Service
public class ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    public Optional<Valoracion> buscarPorUsuarioYArticulo(Usuario usuario, Articulo articulo) {
        return valoracionRepository.findByUsuarioAndArticulo(usuario, articulo);
    }

    public List<Valoracion> obtenerPorArticulo(Articulo articulo) {
        return valoracionRepository.findByArticulo(articulo);
    }

    public Valoracion guardar(Valoracion valoracion) {
        return valoracionRepository.save(valoracion);
    }
}
