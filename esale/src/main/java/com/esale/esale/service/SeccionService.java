package com.esale.esale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Seccion;
import com.esale.esale.repository.SeccionRepository;

@Service
public class SeccionService {

    @Autowired
    private SeccionRepository seccionRepository;

    public List<Seccion> obtenerTodas() {
        return seccionRepository.findAll();
    }

    public Optional<Seccion> buscarPorId(Long id) {
        return seccionRepository.findById(id);
    }

    public Seccion guardar(Seccion seccion) {
        return seccionRepository.save(seccion);
    }

    public void eliminar(Long id) {
        seccionRepository.deleteById(id);
    }
}
