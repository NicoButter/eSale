package com.esale.esale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Tarjeta;
import com.esale.esale.model.Usuario;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    List<Tarjeta> findByUsuario(Usuario usuario);
}
