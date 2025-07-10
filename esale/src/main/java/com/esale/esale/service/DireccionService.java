package com.esale.esale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Direccion;
import com.esale.esale.model.EmpresaTransporte;
import com.esale.esale.model.Usuario;
import com.esale.esale.repository.DireccionRepository;

@Service
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    public List<Direccion> obtenerPorUsuario(Usuario usuario) {
        return direccionRepository.findByUsuario(usuario);
    }

    public List<Direccion> obtenerPorEmpresa(EmpresaTransporte empresa) {
        return direccionRepository.findByEmpresa(empresa);
    }

    public Direccion guardar(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    public void eliminar(Long id) {
        direccionRepository.deleteById(id);
    }
}
