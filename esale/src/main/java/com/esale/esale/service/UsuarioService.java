package com.esale.esale.service;

import com.esale.esale.model.Usuario;
import com.esale.esale.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        // Hasheamos la contraseña
        String hashed = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(hashed);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean validarCredenciales(String email, String passwordPlano) {
        Optional<Usuario> opt = usuarioRepository.findByEmail(email);
        if (opt.isPresent()) {
            Usuario usuario = opt.get();
            return passwordEncoder.matches(passwordPlano, usuario.getPassword());
        }
        return false;
    }
}
