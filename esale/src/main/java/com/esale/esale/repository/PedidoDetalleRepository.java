package com.esale.esale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Pedido;
import com.esale.esale.model.PedidoDetalle;

public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {
    List<PedidoDetalle> findByPedido(Pedido pedido);
}
