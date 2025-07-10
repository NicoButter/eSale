package com.esale.esale.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String nombre;
    private String dni;
    private String email;
    private String password;
}
