package com.esale.esale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.EmpresaTransporte;
import com.esale.esale.model.Telefono;
import com.esale.esale.model.Usuario;
import com.esale.esale.repository.TelefonoRepository;

@Service
public class TelefonoService {

    @Autowired
    private TelefonoRepository telefonoRepository;

    public List<Telefono> obtenerPorUsuario(Usuario usuario) {
        return telefonoRepository.findByUsuario(usuario);
    }

    public List<Telefono> obtenerPorEmpresa(EmpresaTransporte empresa) {
        return telefonoRepository.findByEmpresa(empresa);
    }

    public Telefono guardar(Telefono telefono) {
        return telefonoRepository.save(telefono);
    }

    public void eliminar(Long id) {
        telefonoRepository.deleteById(id);
    }
}
