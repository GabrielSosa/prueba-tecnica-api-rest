package com.devsu.dto;

import com.devsu.dto.CuentaConMovimientos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EstadoDeCuenta {
    private Cliente cliente;
    private List<CuentaConMovimientos> cuentasConMovimientos;
}

