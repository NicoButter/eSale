package com.esale.esale.controller;

import com.esale.esale.model.Usuario;
import com.esale.esale.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.obtenerTodos();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }

    @PutMapping("/{id}/rol")
    public Usuario cambiarRol(@PathVariable Long id, @RequestParam String rol) {
        return usuarioService.actualizarRol(id, rol);
    }
}
