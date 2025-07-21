package com.esale.esale.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esale.esale.dto.LoginRequestDTO;
import com.esale.esale.dto.LoginResponseDTO;
import com.esale.esale.model.Usuario;
import com.esale.esale.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest, HttpSession session) {
        Optional<Usuario> optUsuario = usuarioService.buscarPorEmail(loginRequest.getEmail());

        if (optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();
            boolean passwordOk = usuarioService.validarCredenciales(loginRequest.getEmail(),
                    loginRequest.getPassword());
            if (passwordOk) {
                // Guardar en sesión
                session.setAttribute("usuarioId", usuario.getId());
                session.setAttribute("usuarioRol", usuario.getRol());
                session.setAttribute("usuarioNombre", usuario.getNombre());

                return ResponseEntity.ok(
                        new LoginResponseDTO(
                                usuario.getId(),
                                usuario.getNombre(),
                                usuario.getEmail(),
                                usuario.getRol()));

            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Sesión cerrada correctamente");
    }

}
