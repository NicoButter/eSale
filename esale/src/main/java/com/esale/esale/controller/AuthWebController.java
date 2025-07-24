package com.esale.esale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.esale.esale.dto.LoginRequestDTO;
import com.esale.esale.dto.LoginResponseDTO;
import com.esale.esale.model.Usuario;
import com.esale.esale.service.UsuarioService;

@Controller
public class AuthWebController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {

        String email = request.getEmail();
        String passwordPlano = request.getPassword();

        boolean credencialesValidas = usuarioService.validarCredenciales(email, passwordPlano);

        if (credencialesValidas) {
            Usuario usuario = usuarioService.buscarPorEmail(email)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            LoginResponseDTO response = new LoginResponseDTO();
            response.setId(usuario.getId());
            response.setNombre(usuario.getNombre());
            response.setEmail(usuario.getEmail());
            response.setRol(usuario.getRol());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
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
}
