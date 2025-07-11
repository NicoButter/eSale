package com.esale.esale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.EmpresaTransporte;
import com.esale.esale.repository.EmpresaTransporteRepository;

@Service
public class EmpresaTransporteService {

    @Autowired
    private EmpresaTransporteRepository empresaRepository;

    public List<EmpresaTransporte> obtenerTodas() {
        return empresaRepository.findAll();
    }

    public EmpresaTransporte guardar(EmpresaTransporte empresa) {
        return empresaRepository.save(empresa);
    }

    public Optional<EmpresaTransporte> buscarPorNombre(String nombre) {
        return empresaRepository.findByNombre(nombre);
    }

    public void eliminar(Long id) {
        empresaRepository.deleteById(id);
    }

}
