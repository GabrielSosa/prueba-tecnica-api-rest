package com.devsu.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {
    private Long clienteId;
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contrasena;
    private boolean estado;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El género no puede estar vacío")
    private String genero;
    @Min(value = 18, message = "La edad mínima es 18 años")
    @Max(value = 100, message = "La edad máxima es 100 años")
    private int edad;
    @NotBlank(message = "La identificación no puede estar vacía")
    @Pattern(regexp = "^[0-9]+$", message = "La identificación debe ser numérica")
    private String identificacion;
    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;
    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El teléfono debe ser numérico")
    private String telefono;
}
