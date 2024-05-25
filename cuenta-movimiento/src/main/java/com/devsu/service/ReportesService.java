package com.devsu.service;

import com.devsu.domain.Cuenta;
import com.devsu.domain.Movimientos;
import com.devsu.dto.Cliente;
import com.devsu.dto.CuentaConMovimientos;
import com.devsu.dto.EstadoDeCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.devsu.repository.MovimientosRepository;
import com.devsu.repository.CuentaRepository;

@Service
public class ReportesService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientosRepository movimientosRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cliente.service.url}")
    private String clienteServiceUrl;

    public EstadoDeCuenta generarReporte(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long clienteId) {
        // Obtener información del cliente desde otro microservicio
        Cliente cliente = obtenerCliente(clienteId);

        // Obtener cuentas del cliente
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

        List<CuentaConMovimientos> cuentasConMovimientos = cuentas.stream().map(cuenta -> {
            List<Movimientos> movimientos = movimientosRepository.findByCuentaIdAndFechaBetween(cuenta.getId(), fechaInicio, fechaFin);
            return new CuentaConMovimientos(cuenta, movimientos);
        }).collect(Collectors.toList());

        // Construir el estado de cuenta
        return new EstadoDeCuenta(cliente, cuentasConMovimientos);
    }

    private Cliente obtenerCliente(Long clienteId) {
        String url = clienteServiceUrl + "/" + clienteId;
        return restTemplate.getForObject(url, Cliente.class);
    }
}
