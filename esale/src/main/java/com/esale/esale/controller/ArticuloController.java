package com.esale.esale.controller;

import com.esale.esale.model.Articulo;
import com.esale.esale.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articulos")
@CrossOrigin(origins = "*")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping
    public List<Articulo> listarTodos() {
        return articuloService.obtenerTodos();
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
            art.setMarca(actualizado.getMarca());
            art.setModelo(actualizado.getModelo());
            art.setPrecioSinIVA(actualizado.getPrecioSinIVA());
            art.setDescripcion(actualizado.getDescripcion());
            return ResponseEntity.ok(articuloService.guardar(art));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        articuloService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
