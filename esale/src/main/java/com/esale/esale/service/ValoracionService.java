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

    public Valoracion agregarValoracion(Usuario usuario, Articulo articulo, int puntuacion) {
        // Verificamos si ya existe una valoración del mismo usuario para el mismo
        // artículo
        Optional<Valoracion> existente = valoracionRepository.findByUsuarioAndArticulo(usuario, articulo);

        if (existente.isPresent()) {
            throw new RuntimeException("El usuario ya ha valorado este artículo.");
        }

        Valoracion valoracion = new Valoracion();
        valoracion.setUsuario(usuario);
        valoracion.setArticulo(articulo);
        valoracion.setPuntuacion(puntuacion);
        return valoracionRepository.save(valoracion);
    }

    public double calcularPromedio(Articulo articulo) {
        List<Valoracion> valoraciones = valoracionRepository.findByArticulo(articulo);

        if (valoraciones.isEmpty())
            return 0.0;

        double suma = valoraciones.stream()
                .mapToInt(Valoracion::getPuntuacion)
                .sum();

        return suma / valoraciones.size();
    }

}
