package com.esale.esale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Articulo;
import com.esale.esale.model.Comentario;
import com.esale.esale.model.Usuario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByArticulo(Articulo articulo);
    List<Comentario> findByUsuario(Usuario usuario);
}
