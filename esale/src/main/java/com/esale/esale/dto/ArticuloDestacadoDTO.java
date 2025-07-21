package com.esale.esale.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticuloDestacadoDTO {
    private Long id;
    private ArticuloDTO articulo; // El DTO de arriba
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public ArticuloDestacadoDTO(com.esale.esale.model.ArticuloDestacado destacado) {
        this.id = destacado.getId();
        this.articulo = new ArticuloDTO(destacado.getArticulo());
        this.fechaInicio = destacado.getFechaInicio();
        this.fechaFin = destacado.getFechaFin();
    }

}
