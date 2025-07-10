package com.esale.esale.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Direccion;
import com.esale.esale.model.EmpresaTransporte;
import com.esale.esale.model.Usuario;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    List<Direccion> findByUsuario(Usuario usuario);
    List<Direccion> findByEmpresa(EmpresaTransporte empresa);
}
