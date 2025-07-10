package com.esale.esale.model;

import jakarta.persistence.Column;
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
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // Visa, Mastercard, etc.

    @Column(length = 16)
    private String numero;

    private int mesVencimiento;
    private int anioVencimiento;

    @ManyToOne
    private Usuario usuario;

}
