package com.esale.esale.dto;

import com.esale.esale.model.Marca;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarcaDTO {
    private Long id;
    private String nombre;

    public MarcaDTO(Marca marca) {
        this.id = marca.getId();
        this.nombre = marca.getNombre();
    }
}
