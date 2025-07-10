package com.esale.esale.controller;

import com.esale.esale.dto.LoginRequestDTO;
import com.esale.esale.dto.RegisterRequestDTO;
import com.esale.esale.model.Usuario;
import com.esale.esale.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")  // Para pruebas con Postman o frontend externo
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody RegisterRequestDTO request) {
        if (usuarioService.buscarPorEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("El email ya está registrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setDni(request.getDni());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword()); // El servicio ya la hashea
        usuario.setRol("CLIENTE"); // Valor por defecto

        Usuario guardado = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok("Usuario registrado con éxito.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        boolean valido = usuarioService.validarCredenciales(request.getEmail(), request.getPassword());

        if (valido) {
            Usuario usuario = usuarioService.buscarPorEmail(request.getEmail()).get();
            return ResponseEntity.ok(usuario); // O podés devolver solo nombre y rol, si preferís
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
