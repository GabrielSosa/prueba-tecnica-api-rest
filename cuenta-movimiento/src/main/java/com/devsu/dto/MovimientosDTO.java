package com.devsu.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientosDTO {
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private float valor;
    private String numeroCuenta;
}
