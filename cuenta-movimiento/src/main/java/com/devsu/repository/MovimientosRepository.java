package com.devsu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsu.domain.Movimientos;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {
}