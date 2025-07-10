package com.esale.esale.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.AsignacionTransporte;
import com.esale.esale.model.Pedido;
import com.esale.esale.repository.AsignacionTransporteRepository;

@Service
public class AsignacionTransporteService {

    @Autowired
    private AsignacionTransporteRepository asignacionRepository;

    public AsignacionTransporte guardar(AsignacionTransporte asignacion) {
        return asignacionRepository.save(asignacion);
    }

    public Optional<AsignacionTransporte> buscarPorPedido(Pedido pedido) {
        return asignacionRepository.findByPedido(pedido);
    }
}
