package com.esale.esale.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Usuario;
import com.esale.esale.service.UsuarioService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Podés usar email si querés, cambiando la lógica de búsqueda
        Usuario usuario = usuarioService.buscarPorEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        return new CustomUserDetails(usuario);
    }
}
