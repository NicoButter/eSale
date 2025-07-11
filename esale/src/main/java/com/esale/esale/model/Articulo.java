package com.esale.esale.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoArticulo;

    private String marca;
    private String modelo;
    private String descripcion;
    private Double precioSinIVA;
    private Integer stock;

    @ManyToOne
    private Seccion seccion;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<EspecificacionTecnica> especificaciones;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<Valoracion> valoraciones;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<ArticuloDestacado> destacados;

}
