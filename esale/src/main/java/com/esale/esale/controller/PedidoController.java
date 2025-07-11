package com.esale.esale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esale.esale.model.Pedido;
import com.esale.esale.model.Usuario;
import com.esale.esale.service.PedidoService;
import com.esale.esale.service.UsuarioService;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.obtenerTodos();
    }

    @GetMapping("/usuario/{email}")
    public List<Pedido> listarPorUsuario(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email).orElseThrow();
        return pedidoService.buscarPorUsuario(usuario);
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        return pedidoService.guardar(pedido);
    }
}
