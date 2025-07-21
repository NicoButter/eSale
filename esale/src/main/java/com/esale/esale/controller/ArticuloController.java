package com.esale.esale.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.esale.esale.dto.ArticuloDTO;
import com.esale.esale.model.Articulo;
import com.esale.esale.service.ArticuloService;

@Controller
@RequestMapping("/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping
    public List<ArticuloDTO> listarTodos() {
        return articuloService.obtenerTodos().stream()
            .map(ArticuloDTO::new)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> buscarPorId(@PathVariable Long id) {
        Optional<Articulo> articulo = articuloService.buscarPorId(id);
        return articulo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Articulo crear(@RequestBody Articulo articulo) {
        return articuloService.guardar(articulo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> actualizar(@PathVariable Long id, @RequestBody Articulo actualizado) {
        Optional<Articulo> existente = articuloService.buscarPorId(id);
        if (existente.isPresent()) {
            Articulo art = existente.get();
            art.setModelo(actualizado.getModelo());
            art.setPrecioSinIVA(actualizado.getPrecioSinIVA());
            art.setDescripcion(actualizado.getDescripcion());
            art.setStock(actualizado.getStock());
            art.setImageUrl(actualizado.getImageUrl());
            art.setMarca(actualizado.getMarca());
            art.setRebaja(actualizado.getRebaja());
            return ResponseEntity.ok(articuloService.guardar(art));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        articuloService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
