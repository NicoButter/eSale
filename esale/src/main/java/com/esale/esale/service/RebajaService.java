package com.esale.esale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.Rebaja;
import com.esale.esale.model.Seccion;
import com.esale.esale.repository.RebajaRepository;

@Service
public class RebajaService {

    @Autowired
    private RebajaRepository rebajaRepository;

    public List<Rebaja> obtenerTodas() {
        return rebajaRepository.findAll();
    }

    public List<Rebaja> obtenerPorArticulo(Articulo articulo) {
        return rebajaRepository.findByArticulo(articulo);
    }

    public List<Rebaja> obtenerPorSeccion(Seccion seccion) {
        return rebajaRepository.findBySeccion(seccion);
    }

    public List<Rebaja> obtenerRebajasGlobales() {
        return rebajaRepository.findByAplicaATodaLaTiendaTrue();
    }

    public Rebaja guardar(Rebaja rebaja) {
        return rebajaRepository.save(rebaja);
    }
}

