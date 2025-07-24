package com.esale.esale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.Comentario;
import com.esale.esale.model.Usuario;
import com.esale.esale.service.ArticuloService;
import com.esale.esale.service.ComentarioService;
import com.esale.esale.service.UsuarioService;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/{articuloId}")
    public List<Comentario> listarPorArticulo(@PathVariable Long articuloId) {
        Articulo articulo = articuloService.buscarPorId(articuloId).orElseThrow();
        return comentarioService.obtenerPorArticulo(articulo);
    }

    @PostMapping
    public Comentario agregarComentario(@RequestParam String email, @RequestParam Long articuloId, @RequestBody String texto) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        Articulo articulo = articuloService.buscarPorId(articuloId).orElseThrow();
        return comentarioService.agregarComentario(usuario, articulo, texto);
    }
}
