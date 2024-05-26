package com.devsu.repository;

import com.devsu.dto.ClienteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devsu.domain.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByEstadoTrue();
    @Query("SELECT c FROM Cliente c WHERE c.clienteId = :id AND c.estado = true")
    Optional<Cliente> findClienteByIdAndEstadoTrue(@Param("id") Long id);
}