package com.esale.esale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Pedido;
import com.esale.esale.model.PedidoDetalle;
import com.esale.esale.repository.PedidoDetalleRepository;

@Service
public class PedidoDetalleService {

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;

    public List<PedidoDetalle> obtenerPorPedido(Pedido pedido) {
        return pedidoDetalleRepository.findByPedido(pedido);
    }

    public PedidoDetalle guardar(PedidoDetalle detalle) {
        return pedidoDetalleRepository.save(detalle);
    }

    public void eliminar(Long id) {
        pedidoDetalleRepository.deleteById(id);
    }
}
