package com.devsu.dto;

import com.devsu.domain.Cuenta;
import com.devsu.domain.Movimientos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CuentaConMovimientos {
    private Cuenta cuenta;
    private List<Movimientos> movimientos;
}

