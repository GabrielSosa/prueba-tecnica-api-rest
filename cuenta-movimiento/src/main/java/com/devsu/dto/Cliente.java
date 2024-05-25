package com.devsu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private Long clienteId;
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;

    // Getters and setters
}

