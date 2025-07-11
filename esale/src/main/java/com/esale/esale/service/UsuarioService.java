package com.esale.esale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Usuario;
import com.esale.esale.repository.UsuarioRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        // Hash de la contraseña
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

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario actualizarRol(Long id, String nuevoRol) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        usuario.setRol(nuevoRol);
        return usuarioRepository.save(usuario);
    }

}
