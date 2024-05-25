package com.devsu.controller;



import com.devsu.domain.Cuenta;
import com.devsu.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        Optional<Cuenta> cuenta = cuentaService.findById(id);
        return cuenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.save(cuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        Optional<Cuenta> cuentaOptional = cuentaService.findById(id);

        if (!cuentaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        cuenta.setId(id);
        return ResponseEntity.ok(cuentaService.save(cuenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        if (!cuentaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        cuentaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
