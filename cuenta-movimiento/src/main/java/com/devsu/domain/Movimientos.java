package com.devsu.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "movimientos")
public class Movimientos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private LocalDateTime fecha;

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    @Column(name = "tipo_movimiento", nullable = false)
    private String tipoMovimiento;

    @NotNull(message = "El valor es obligatorio")
    @Column(nullable = false)
    private float valor;

    @Column(nullable = false)
    private float saldo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;
}
