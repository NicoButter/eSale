package com.esale.esale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
public class Rebaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double porcentajeDescuento;

    private boolean aplicaATodaLaTienda;

    @ManyToOne
    private Seccion seccion;

    @ManyToOne
    private Articulo articulo;

}
