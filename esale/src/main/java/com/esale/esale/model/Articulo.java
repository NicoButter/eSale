package com.esale.esale.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo; // Código único
    private String modelo;
    private BigDecimal precioSinIVA;
    private String descripcion;
    private int stock;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @OneToOne
    @JoinColumn(name = "rebaja_id")
    private Rebaja rebaja;

    @OneToMany(mappedBy = "articulo")
    private List<ArticuloDestacado> destacados;

}
