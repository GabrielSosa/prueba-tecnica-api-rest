package com.devsu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsu.domain.Cuenta;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByClienteId(Long clienteId);
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

}