package com.devsu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {
    private Long clienteId;
    private String contrasena;
    private boolean estado;
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
