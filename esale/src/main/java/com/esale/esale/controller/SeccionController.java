package com.esale.esale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esale.esale.model.Seccion;
import com.esale.esale.service.SeccionService;

@RestController
@RequestMapping("/secciones")
@CrossOrigin(origins = "*")
public class SeccionController {

    @Autowired
    private SeccionService seccionService;

    @GetMapping
    public List<Seccion> listar() {
        return seccionService.obtenerTodas();
    }

    @PostMapping
    public Seccion crear(@RequestBody Seccion seccion) {
        return seccionService.guardar(seccion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        seccionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
