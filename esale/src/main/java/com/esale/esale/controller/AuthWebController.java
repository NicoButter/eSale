package com.esale.esale.controller;

import com.esale.esale.model.Usuario;
import com.esale.esale.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthWebController {

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "usuario/login";
    }

    // Mostrar registro
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/registro";
    }

    // Procesar registro
    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuario usuario, Model model) {
        if (usuarioService.buscarPorEmail(usuario.getEmail()).isPresent()) {
            model.addAttribute("error", "El email ya está registrado.");
            return "usuario/registro";
        }

        usuarioService.registrarUsuario(usuario);
        return "redirect:/login";
    }

    // Procesar login manual (solo si no usás Spring Security)
    @PostMapping("/login")
    public String procesarLogin(
        @RequestParam String email,
        @RequestParam String password,
        Model model
    ) {
        boolean valido = usuarioService.validarCredenciales(email, password);

        if (valido) {
            Usuario usuario = usuarioService.buscarPorEmail(email).get();
            model.addAttribute("usuario", usuario); // opcional, si querés mostrarlo
            return "redirect:/"; // o `/dashboard` según el rol
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "usuario/login";
        }
    }
}
