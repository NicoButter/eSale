package com.esale.esale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esale.esale.model.EmpresaTransporte;


public interface EmpresaTransporteRepository extends JpaRepository<EmpresaTransporte, Long> {
    
    Optional<EmpresaTransporte> findByNombre(String nombre);

}
