package com.esale.esale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.Usuario;
import com.esale.esale.model.Valoracion;
import com.esale.esale.service.ArticuloService;
import com.esale.esale.service.UsuarioService;
import com.esale.esale.service.ValoracionService;

@RestController
@RequestMapping("/valoraciones")
@CrossOrigin(origins = "*")
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ArticuloService articuloService;

    @PostMapping
    public Valoracion valorar(@RequestParam String email, @RequestParam Long articuloId, @RequestParam int puntuacion) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        Articulo articulo = articuloService.buscarPorId(articuloId).orElseThrow();
        return valoracionService.agregarValoracion(usuario, articulo, puntuacion);
    }

    @GetMapping("/promedio/{articuloId}")
    public double promedio(@PathVariable Long articuloId) {
        Articulo articulo = articuloService.buscarPorId(articuloId).orElseThrow();
        return valoracionService.calcularPromedio(articulo);
    }
}
