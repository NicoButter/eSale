package com.esale.esale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.EmpresaTransporte;
import com.esale.esale.model.Telefono;
import com.esale.esale.model.Usuario;

public interface TelefonoRepository extends JpaRepository<Telefono, Long> {
    List<Telefono> findByUsuario(Usuario usuario);
    List<Telefono> findByEmpresa(EmpresaTransporte empresa);
}
