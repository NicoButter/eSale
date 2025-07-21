package com.esale.esale.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticuloDTO {
    private Long id;
    private String codigo;
    private String modelo;
    private BigDecimal precioSinIVA;
    private String descripcion;
    private int stock;
    private String imageUrl;
    private String marcaNombre; // Solo el nombre o id de la marca
    private String rebajaDescripcion; // Por ejemplo
    private Long seccionId; // o nombre, según prefieras

    private MarcaDTO marca;

    // Constructor desde entidad
    public ArticuloDTO(com.esale.esale.model.Articulo articulo) {
        this.id = articulo.getId();
        this.codigo = articulo.getCodigo();
        this.modelo = articulo.getModelo();
        this.precioSinIVA = articulo.getPrecioSinIVA();
        this.descripcion = articulo.getDescripcion();
        this.stock = articulo.getStock();
        this.imageUrl = articulo.getImageUrl();
        this.marcaNombre = articulo.getMarca() != null ? articulo.getMarca().getNombre() : null;
        this.seccionId = articulo.getSeccion() != null ? articulo.getSeccion().getId() : null;

        if (articulo.getMarca() != null) {
            this.marca = new MarcaDTO(articulo.getMarca());
        }
    }
}