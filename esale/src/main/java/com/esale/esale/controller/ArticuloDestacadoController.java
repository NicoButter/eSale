package com.esale.esale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esale.esale.model.ArticuloDestacado;
import com.esale.esale.service.ArticuloDestacadoService;

@RestController
@RequestMapping("/destacados")
@CrossOrigin(origins = "*")
public class ArticuloDestacadoController {

    @Autowired
    private ArticuloDestacadoService destacadoService;

    @GetMapping
    public List<ArticuloDestacado> listarTodos() {
        return destacadoService.obtenerTodos();
    }

    @GetMapping("/hoy")
    public List<ArticuloDestacado> destacadosHoy() {
        return destacadoService.obtenerDestacadosHoy();
    }
}
