package com.esale.esale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.AsignacionTransporte;
import com.esale.esale.model.Pedido;

public interface AsignacionTransporteRepository extends JpaRepository<AsignacionTransporte, Long> {
    Optional<AsignacionTransporte> findByPedido(Pedido pedido);
}
