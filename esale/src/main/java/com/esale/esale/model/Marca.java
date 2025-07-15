package com.esale.esale.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Nombre de la marca (e.g., Samsung, Xiaomi)
    private String paisOrigen; // Atributo adicional opcional

    @OneToMany(mappedBy = "marca")
    private List<Articulo> articulos;

}