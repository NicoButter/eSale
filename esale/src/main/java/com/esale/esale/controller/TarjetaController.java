package com.esale.esale.controller;

import com.esale.esale.model.Tarjeta;
import com.esale.esale.model.Usuario;
import com.esale.esale.service.TarjetaService;
import com.esale.esale.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarjetas")
@CrossOrigin(origins = "*")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{email}")
    public List<Tarjeta> listarPorUsuario(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email).orElseThrow();
        return tarjetaService.obtenerPorUsuario(usuario);
    }

    @PostMapping("/{email}")
    public Tarjeta agregar(@PathVariable String email, @RequestBody Tarjeta tarjeta) {
        Usuario usuario = usuarioService.buscarPorEmail(email).orElseThrow();
        tarjeta.setUsuario(usuario);
        return tarjetaService.guardar(tarjeta);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        tarjetaService.eliminar(id);
    }
}
