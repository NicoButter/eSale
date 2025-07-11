package com.esale.esale.controller;

import com.esale.esale.model.*;
import com.esale.esale.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private CarritoDetalleService detalleService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/{email}")
    public List<CarritoDetalle> verCarrito(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email).orElseThrow();
        Carrito carrito = carritoService.obtenerPorUsuario(usuario).orElseGet(() -> carritoService.crearCarrito(usuario));
        return detalleService.obtenerItemsPorCarrito(carrito);
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarItem(@RequestParam String email, @RequestParam Long articuloId, @RequestParam int cantidad) {
        Usuario usuario = usuarioService.buscarPorEmail(email).orElseThrow();
        Carrito carrito = carritoService.obtenerPorUsuario(usuario).orElseGet(() -> carritoService.crearCarrito(usuario));
        Articulo articulo = articuloService.buscarPorId(articuloId).orElseThrow();
        carritoService.agregarAlCarrito(carrito, articulo, cantidad);
        return ResponseEntity.ok("Artículo agregado");
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarItem(@RequestParam String email, @RequestParam Long articuloId) {
        Usuario usuario = usuarioService.buscarPorEmail(email).orElseThrow();
        Carrito carrito = carritoService.obtenerPorUsuario(usuario).orElseThrow();
        Articulo articulo = articuloService.buscarPorId(articuloId).orElseThrow();
        carritoService.eliminarDelCarrito(carrito, articulo);
        return ResponseEntity.ok("Artículo eliminado");
    }
}
