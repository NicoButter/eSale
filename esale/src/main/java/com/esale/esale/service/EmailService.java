package com.esale.esale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esale.esale.model.Email;
import com.esale.esale.model.EmpresaTransporte;
import com.esale.esale.model.Usuario;
import com.esale.esale.repository.EmailRepository;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public List<Email> obtenerPorUsuario(Usuario usuario) {
        return emailRepository.findByUsuario(usuario);
    }

    public List<Email> obtenerPorEmpresa(EmpresaTransporte empresa) {
        return emailRepository.findByEmpresa(empresa);
    }

    public Optional<Email> buscarPorDireccion(String direccion) {
        return emailRepository.findByDireccion(direccion);
    }

    public Email guardar(Email email) {
        return emailRepository.save(email);
    }

    public void eliminar(Long id) {
        emailRepository.deleteById(id);
    }
}
