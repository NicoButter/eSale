package com.esale.esale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esale.esale.dto.ArticuloDestacadoDTO;
import com.esale.esale.model.ArticuloDestacado;
import com.esale.esale.service.ArticuloDestacadoService;

@RestController
@RequestMapping("/articulos/destacados") // Cambio clave aquí
public class ArticuloDestacadoController {

   @Autowired
    private ArticuloDestacadoService destacadoService;

    @GetMapping
    public List<ArticuloDestacadoDTO> listarTodos() {
        return destacadoService.obtenerTodos().stream()
            .map(ArticuloDestacadoDTO::new)
            .toList();
    }

    @GetMapping("/hoy")
    public List<ArticuloDestacado> destacadosHoy() {
        return destacadoService.obtenerDestacadosHoy();
    }
}