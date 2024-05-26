package com.devsu.controller;

import com.devsu.domain.Cuenta;
import com.devsu.domain.Movimientos;
import com.devsu.dto.MovimientosDTO;
import com.devsu.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import com.devsu.domain.Movimientos;
import com.devsu.service.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientosController {

    @Autowired
    private MovimientoService movimientosService;

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Movimientos> getAllMovimientos() {
        return movimientosService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimientos> getMovimientosById(@PathVariable Long id) {
        Optional<Movimientos> movimientos = movimientosService.findById(id);
        return movimientos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createMovimientos(@Valid @RequestBody MovimientosDTO movimientosDTO, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Error en la solicitud: " + result.getAllErrors());
        }

        Optional<Cuenta> cuentaOptional = cuentaService.findByNumeroCuenta(movimientosDTO.getNumeroCuenta());

        if (!cuentaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cuenta no encontrada");
        }

        Cuenta cuenta = cuentaOptional.get();
        float nuevoSaldo = cuenta.getSaldoInicial() + movimientosDTO.getValor();

        if (nuevoSaldo < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaService.save(cuenta);

        Movimientos movimientos = new Movimientos();
        movimientos.setFecha(movimientosDTO.getFecha());
        movimientos.setTipoMovimiento(movimientosDTO.getTipoMovimiento());
        movimientos.setValor(movimientosDTO.getValor());
        movimientos.setSaldo(nuevoSaldo);
        movimientos.setCuenta(cuenta);

        Movimientos savedMovimiento = movimientosService.save(movimientos);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovimientos(@Valid @RequestBody MovimientosDTO movimientosDTO, @PathVariable long id, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Error en la solicitud: " + result.getAllErrors());
        }

        Optional<Cuenta> cuentaOptional = cuentaService.findByNumeroCuenta(movimientosDTO.getNumeroCuenta());

        if (!cuentaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cuenta no encontrada");
        }

        Cuenta cuenta = cuentaOptional.get();

        Optional<Movimientos> movimientoOptional = movimientosService.findById(id);

        if (!movimientoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Movimientos existingMovimiento = movimientoOptional.get();
        float valorDiferencia = movimientosDTO.getValor() - existingMovimiento.getValor();

        float nuevoSaldo = cuenta.getSaldoInicial() + valorDiferencia;

        if (nuevoSaldo < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaService.save(cuenta);

        existingMovimiento.setTipoMovimiento(movimientosDTO.getTipoMovimiento());
        existingMovimiento.setValor(movimientosDTO.getValor());
        existingMovimiento.setSaldo(nuevoSaldo);
        existingMovimiento.setCuenta(cuenta);

        Movimientos updatedMovimiento = movimientosService.save(existingMovimiento);

        return ResponseEntity.ok(updatedMovimiento);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateMovimientos(@PathVariable Long id, @RequestBody Movimientos movimientos) {
        Optional<Movimientos> movimientosOptional = movimientosService.findById(id);

        if (!movimientosOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Movimientos existingMovimiento = movimientosOptional.get();
        Cuenta cuenta = existingMovimiento.getCuenta();

        if (movimientos.getValor() != 0) {
            float valorDiferencia = movimientos.getValor() - existingMovimiento.getValor();
            float nuevoSaldo = cuenta.getSaldoInicial() + valorDiferencia;

            if (nuevoSaldo < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo no disponible");
            }

            cuenta.setSaldoInicial(nuevoSaldo);
            cuentaService.save(cuenta);

            existingMovimiento.setValor(movimientos.getValor());
            existingMovimiento.setSaldo(nuevoSaldo);
        }

        if (movimientos.getFecha() != null) {
            existingMovimiento.setFecha(movimientos.getFecha());
        }
        if (movimientos.getTipoMovimiento() != null) {
            existingMovimiento.setTipoMovimiento(movimientos.getTipoMovimiento());
        }

        Movimientos updatedMovimiento = movimientosService.save(existingMovimiento);
        return ResponseEntity.ok(updatedMovimiento);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimientos(@PathVariable Long id) {
        if (!movimientosService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        movimientosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
