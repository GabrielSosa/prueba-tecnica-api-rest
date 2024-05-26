package com.devsu.controller;



import com.devsu.domain.Cuenta;
import com.devsu.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable String id) {
        Optional<Cuenta> cuenta = cuentaService.findByNumeroCuenta(id);
        return cuenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCuenta(@Valid @RequestBody Cuenta cuenta, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Error en la solicitud: " + result.getAllErrors());
        }
        Cuenta nuevaCuenta = cuentaService.save(cuenta);
        return ResponseEntity.ok(nuevaCuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCuenta(@PathVariable String id, @Valid @RequestBody Cuenta cuenta,
                                          BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Error en la solicitud: " + result.getAllErrors());
        }
        Optional<Cuenta> cuentaOptional = cuentaService.findByNumeroCuenta(id.trim());

        if (!cuentaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Cuenta existingCuenta = cuentaOptional.get();
        existingCuenta.setTipoCuenta(cuenta.getTipoCuenta());
        existingCuenta.setSaldoInicial(cuenta.getSaldoInicial());
        existingCuenta.setEstado(cuenta.getEstado());
        existingCuenta.setClienteId(cuenta.getClienteId());

        return ResponseEntity.ok(cuentaService.save(existingCuenta));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable Long id) {
        if (!cuentaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        cuentaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
