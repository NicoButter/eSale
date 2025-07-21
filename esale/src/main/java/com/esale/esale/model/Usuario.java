package com.esale.esale.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false, length = 20)
    private String dni;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String rol; // ADMIN o CLIENTE

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Telefono> telefonos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Email> emails;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tarjeta> tarjetas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

}
