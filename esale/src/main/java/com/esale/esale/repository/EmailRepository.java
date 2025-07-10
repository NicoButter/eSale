package com.esale.esale.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.Email;
import com.esale.esale.model.EmpresaTransporte;
import com.esale.esale.model.Usuario;

public interface EmailRepository extends JpaRepository<Email, Long> {
    Optional<Email> findByDireccion(String direccion);
    List<Email> findByUsuario(Usuario usuario);
    List<Email> findByEmpresa(EmpresaTransporte empresa);
}
