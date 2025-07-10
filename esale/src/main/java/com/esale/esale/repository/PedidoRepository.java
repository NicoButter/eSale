package com.esale.esale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Pedido;
import com.esale.esale.model.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuario(Usuario usuario);
}
