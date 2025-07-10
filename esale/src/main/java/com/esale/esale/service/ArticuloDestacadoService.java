package com.esale.esale.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.ArticuloDestacado;
import com.esale.esale.repository.ArticuloDestacadoRepository;

@Service
public class ArticuloDestacadoService {

    @Autowired
    private ArticuloDestacadoRepository destacadoRepository;

    public List<ArticuloDestacado> obtenerTodos() {
        return destacadoRepository.findAll();
    }

    public List<ArticuloDestacado> obtenerPorArticulo(Articulo articulo) {
        return destacadoRepository.findByArticulo(articulo);
    }

    public List<ArticuloDestacado> obtenerDestacadosHoy() {
        LocalDate hoy = LocalDate.now();
        return destacadoRepository.findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(hoy, hoy);
    }

    public ArticuloDestacado guardar(ArticuloDestacado destacado) {
        return destacadoRepository.save(destacado);
    }

    public void eliminar(Long id) {
        destacadoRepository.deleteById(id);
    }
}
