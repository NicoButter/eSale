package com.esale.esale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.Comentario;
import com.esale.esale.repository.ComentarioRepository;


@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> obtenerPorArticulo(Articulo articulo) {
        return comentarioRepository.findByArticulo(articulo);
    }

    public Comentario guardar(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }
}
