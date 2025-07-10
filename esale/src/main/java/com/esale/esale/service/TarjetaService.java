package com.esale.esale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Tarjeta;
import com.esale.esale.model.Usuario;
import com.esale.esale.repository.TarjetaRepository;

@Service
public class TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    public List<Tarjeta> obtenerPorUsuario(Usuario usuario) {
        return tarjetaRepository.findByUsuario(usuario);
    }

    public Tarjeta guardar(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }
}

