package com.devsu.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 7, fraction = 0, message = "El número de cuenta debe ser un número entero de hasta 7 dígitos")
    @NotBlank(message = "El número de cuenta es obligatorio")
    @Column(name = "numero_cuenta", nullable = false, unique = true)
    private String numeroCuenta;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    @Column(name = "tipo_cuenta", nullable = false)
    private String tipoCuenta;

    @DecimalMin(value = "0.0", inclusive = true, message = "El saldo inicial debe ser un número positivo o cero")
    @Column(name = "saldo_inicial", nullable = false)
    private float saldoInicial;

    @NotBlank(message = "El estado de la cuenta es obligatorio")
    @Column(name = "estado", nullable = false)
    private String estado;

    @NotNull(message = "El ID del cliente es obligatorio")
    @Column(name = "cliente_Id", nullable = false)
    private Long clienteId;


}